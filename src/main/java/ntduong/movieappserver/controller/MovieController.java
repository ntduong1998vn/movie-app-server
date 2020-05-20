package ntduong.movieappserver.controller;

import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.service.impl.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService, ModelMapper modelMapper) {
        this.movieService = movieService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    Page<MovieDTO> getListByPage(@RequestParam(name = "currentPage",defaultValue = "0") int page,
                                              @RequestParam(name = "pageSize", defaultValue = "6") int size) {
        if(page >0) page=page-1;
        System.out.println("****************************************************");
        System.out.println("Page , Size : " + page + " " + size);

        // TODO handle Error
        return movieService.findAll(page, size);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDTO getOne(@PathVariable int id) {
        System.out.println("******************* Movie ID: " + id + " ****************************");
        MovieDTO result = movieService.findById(id);

        if(result !=null)
            return result;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie with ID="+id+" NOT FOUND!");
    }

    @GetMapping("/genre")
    Page<MovieDTO> getMoviesByGenreId(@RequestParam(name = "id", required = true) int id,
                                   @RequestParam(name = "currentPage", defaultValue = "0") int page,
                                   @RequestParam(name = "pageSize", defaultValue = "6") int size) {

        System.out.println("*********** Genre ID: " + id + " *************");
        return movieService.getMoviesByGenreId(id, page, size);
    }

    @GetMapping("/search")
    List<Movie> searchByKeyword(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Movie> result = movieService.findByTitle(keyword);
        return result;
    }

    /// api/movie/advanced?search=title:do,imdb>35&sortBy=views:asc
    @GetMapping("/advanced")
    List<MovieDTO> searchAdvanced(@RequestParam(name = "search",required = false)String search,
                                    @RequestParam(name = "sortBy")String sortBy){
        return movieService.searchCriteria(search);
    }

    @PostMapping("/")
    public ResponseEntity<Movie> saveProduct(@RequestBody Movie movie, UriComponentsBuilder builder) {
        Movie newMovie = movieService.create(movie);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/api/movies/{id}").buildAndExpand(movie.getId()).toUri());
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

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
