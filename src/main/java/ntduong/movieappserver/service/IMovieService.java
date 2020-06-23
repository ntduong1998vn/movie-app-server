package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IMovieService {

    public Page<MovieDTO> findByPage(int page, int size);

    public boolean deleteById(int id);

    public MovieDTO findById(int id);

    public Movie create(Movie movie);

    public boolean update(int movieId, MovieDTO movieDTO);

    public Page<MovieDTO> findByGenreId(int id, int page, int limit);

    public List<Movie> findByTitle(String keyword);

    public List<MovieDTO> searchCriteria(String search);

    public void save(MovieDTO movie,boolean isUpdate);
}
