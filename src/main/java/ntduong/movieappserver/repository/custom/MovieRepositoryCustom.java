package ntduong.movieappserver.repository.custom;

import ntduong.movieappserver.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieRepositoryCustom {

    Movie create(Movie movie);

//    List<Movie> searchMovie(List<SearchCriteria> params);

//    Movie findById(int movieId);

    Page<Movie> findByGenreId(int genreId, Pageable pageRequest);
}
