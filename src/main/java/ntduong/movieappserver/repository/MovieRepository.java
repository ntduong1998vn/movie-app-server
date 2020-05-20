package ntduong.movieappserver.repository;

import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.repository.custom.MovieRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>,
        MovieRepositoryCustom {

    Page<Movie> findMovieByGenres_Id(int genreId, Pageable pageRequest);


    List<Movie> findByTitleIgnoreCaseContaining(String keyword);
}
