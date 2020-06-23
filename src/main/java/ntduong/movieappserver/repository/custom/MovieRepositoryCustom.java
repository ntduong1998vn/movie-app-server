package ntduong.movieappserver.repository.custom;

import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.util.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieRepositoryCustom {

    Movie create(Movie movie);

    void deleteById(int movieId);

    List<Movie> searchMovie(List<SearchCriteria> params);

    public Movie findById(int movieId);

    Page<Movie> findByGenreId(int genreId, Pageable pageRequest);
}
