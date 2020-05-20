package ntduong.movieappserver.repository.custom;

import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.util.SearchCriteria;

import java.util.List;

public interface MovieRepositoryCustom {

    Movie create(Movie movie);

    void deleteById(int movieId);

    List<Movie> searchMovie(List<SearchCriteria> params);
}
