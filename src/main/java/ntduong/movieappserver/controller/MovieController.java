package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
@Api(value = "Movie APIs")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    @ApiOperation(value = "Get list movie by page")
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    Page<MovieDTO> getListByPage(@RequestParam(name = "currentPage", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "6") int size) {

        System.out.println("****************************************************");
        System.out.println("Page , Size : " + page + " " + size);

        if (page > 0) page = page - 1;
        // TODO handle Error
        return movieService.findByPage(page, size);
    }

    @ApiOperation(value = "Get movie by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDTO getOne(@PathVariable int id) {
        System.out.println("******************* Movie ID: " + id + " ****************************");
        MovieDTO result = movieService.findById(id);

        if (result != null) {
            return result;
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with ID=" + id + " NOT FOUND!");
    }

    @ApiOperation("Get list movie by genreId")
    @GetMapping("/genre")
    Page<MovieDTO> findByGenreId(@RequestParam(name = "id", required = true) int id,
                                 @RequestParam(name = "currentPage", defaultValue = "0") int page,
                                 @RequestParam(name = "pageSize", defaultValue = "12") int size) {

        System.out.println("*********** Genre ID: " + id + " *************");
        return movieService.findByGenreId(id, page, size);
    }


    @GetMapping("/search")
    List<Movie> searchByKeyword(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Movie> result = movieService.findByTitle(keyword);
        return result;
    }


    /// api/movie/advanced?search=title:do,imdb>35&sortBy=views:asc
    @GetMapping("/advanced")
    List<MovieDTO> searchAdvanced(@RequestParam(name = "search", required = false) String search,
                                  @RequestParam(name = "sortBy") String sortBy) {
        return movieService.searchCriteria(search);
    }

    @ApiOperation("Add new movie")
    @PostMapping("/")
    public ApiResponse<String> saveMovie(@RequestBody MovieDTO movie) {
        ApiResponse<String> apiResponse = new ApiResponse<>();

        try {
            movieService.save(movie, false);
            apiResponse.setMessage("Success");
            apiResponse.setSuccess(HttpStatus.OK);
        } catch (Exception e) {
            apiResponse.setSuccess(HttpStatus.BAD_REQUEST);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @ApiOperation("Update movie")
    @PutMapping("/{movieId}")
    public ResponseEntity update(@PathVariable int movieId, @RequestBody MovieDTO movie) {
        boolean result = movieService.update(movieId, movie);

        if (result)
            return ResponseEntity.ok(new ApiResponse(HttpStatus.OK, "Update movie successfully!"));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        try {
            movieService.deleteById(id);
            return new ResponseEntity(HttpStatus.RESET_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
