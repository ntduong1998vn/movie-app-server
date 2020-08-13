package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.payload.form.MovieForm;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface IMovieService {

    Page<MovieDTO> findByPage(int page, int size);

    void deleteById(int id) throws Exception;

    MovieDTO findById(int id);

    boolean update(int movieId, MovieDTO movieDTO);

    Page<MovieDTO> findByGenreId(int id, int page, int limit);

    Page<MovieDTO> searchCriteria(String search, int page, int size);

    void save(MovieForm movieForm, boolean isUpdate) throws IllegalArgumentException, IOException;

    void updateStatus(int movieId, boolean value);
}
