package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IMovieService {

    public Page<MovieDTO> findAll(int page, int size);

    public boolean deleteById(int id);

    public MovieDTO findById(int id);

    public Movie create(Movie movie);

    public boolean update(int movieId, MovieDTO movieDTO);

    public Page<Movie> getTopView(int page, int limit);

    public Page<MovieDTO> getMoviesByGenreId(int id, int page, int limit);

    public boolean addGenres(int movieId, List<Integer> addList);

    public boolean removeGenres(int movieId, List<Integer> deleteList);

    public List<Movie> findByTitle(String keyword);

    public List<Movie> findByLetterBegin(String letter);

    public List<MovieDTO> searchCriteria(String search);
}
