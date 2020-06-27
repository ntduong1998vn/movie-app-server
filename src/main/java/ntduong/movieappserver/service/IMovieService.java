package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

interface IMovieService {

    Page<MovieDTO> findByPage(int page, int size);

    boolean deleteById(int id);

    MovieDTO findById(int id);

    Movie create(Movie movie);

    boolean update(int movieId, MovieDTO movieDTO);

    Page<MovieDTO> findByGenreId(int id, int page, int limit);

    List<Movie> findByTitle(String keyword);

    List<MovieDTO> searchCriteria(String search);

    void save(MovieDTO movie,boolean isUpdate);
}
