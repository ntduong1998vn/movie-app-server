package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.repository.custom.MovieRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>, MovieRepositoryCustom {

    Page<Movie> findMovieByGenres_Id(int genreId, Pageable pageable);

    List<Movie> findByTitleIgnoreCaseContaining(String keyword);

//    @Query(value = "select m from Movie m left join fetch m.episodes e where m.id = :movieId")
//    Optional<Movie> findById(int movieId);


}
