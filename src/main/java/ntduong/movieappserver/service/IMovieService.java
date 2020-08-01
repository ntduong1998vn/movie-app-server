package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.payload.form.MovieForm;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface IMovieService {

    Page<MovieDTO> findByPage(int page, int size);

    void deleteById(int id) throws Exception;

    MovieDTO findById(int id);

    boolean update(int movieId, MovieDTO movieDTO);

    Page<MovieDTO> findByGenreId(int id, int page, int limit);

    List<Movie> findByTitle(String keyword);

    List<MovieDTO> searchCriteria(String search);

    void save(MovieForm movieForm, boolean isUpdate) throws IllegalArgumentException, IOException;

    void updateStatus(int movieId,boolean visible);
}
