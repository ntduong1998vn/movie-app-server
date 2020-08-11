package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.payload.form.MovieForm;
import ntduong.movieappserver.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
@Api(value = "Movie APIs")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @ApiOperation(value = "GET LIST BY PAGE")
    @GetMapping("/")
    public ApiResponse<Page<MovieDTO>> getListByPage(@RequestParam(name = "currentPage", defaultValue = "1") int page,
                                                     @RequestParam(name = "pageSize", defaultValue = "6") int size) {
        ApiResponse<Page<MovieDTO>> apiResponse = new ApiResponse<>();
        if (page >= 1 & size >= 1) {
            page--;
            Page<MovieDTO> dtoPage = movieService.findByPage(page, size);
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setResult(dtoPage);
        } else {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage("Giá trị của pageSize và currentPage phải lớn hơn 1");
        }
        return apiResponse;
    }

    @ApiOperation(value = "GET MOVIE BY ID")
    @GetMapping("/{id}")
    public ApiResponse<MovieDTO> getOne(@PathVariable int id) {
        ApiResponse<MovieDTO> apiResponse = new ApiResponse<>();
        if (id > 0) {
            MovieDTO result = movieService.findById(id);
            if (result != null) {
                apiResponse.setSuccess(HttpStatus.OK);
                apiResponse.setResult(result);
            } else {
                apiResponse.setSuccess(HttpStatus.NOT_FOUND);
                apiResponse.setMessage("Không tìm thấy movie với Id: " + id);
            }
        } else {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage("MovieId phải lớn hơn 0");
        }
        return apiResponse;
    }

    @ApiOperation("Get list movie by genreId")
    @GetMapping("/genre")
    Page<MovieDTO> findByGenreId(@RequestParam(name = "id", required = true) int id,
                                 @RequestParam(name = "currentPage", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "12") int size) {
        return movieService.findByGenreId(id, page, size);
    }


    @GetMapping("/search")
    List<Movie> searchByKeyword(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return movieService.findByTitle(keyword);
    }


    /// api/movie/advanced?search=title:do,imdb>35&sortBy=views:asc
    @GetMapping("/advanced")
    List<MovieDTO> searchAdvanced(@RequestParam(name = "search", required = false) String search,
                                  @RequestParam(name = "sortBy") String sortBy) {
        return movieService.searchCriteria(search);
    }

    @ApiOperation("Add new movie")
    @PostMapping(value = "/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> saveMovie(@ModelAttribute MovieForm movieForm) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        try {
            movieService.save(movieForm, false);
            apiResponse.setMessage("Success");
            apiResponse.setSuccess(HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @ApiOperation("Update movie")
    @PutMapping(value = "/{movieId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String update(@PathVariable int movieId, @ModelAttribute MovieForm movieForm) {
        try {
            movieService.save(movieForm, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Thành công!";
    }

    @ApiOperation("Disable / Enable Movie")
    @GetMapping("/{movieId}/status/{value}")
    public ApiResponse<String> updateStatusMovie(@PathVariable int movieId,
                                                 @PathVariable boolean value) {
        movieService.updateStatus(movieId,value);
        return new ApiResponse<>(HttpStatus.OK, "Cập nhật thành công!");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable int id) {
        ApiResponse<String> apiResponse = new ApiResponse<>();
        try {
            movieService.deleteById(id);
            apiResponse.setSuccess(HttpStatus.OK);
            apiResponse.setMessage("Xóa thành công.");
        } catch (Exception e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }

        return apiResponse;
    }

}
