package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {

    List<GenreEntity> findByNameContainsIgnoreCase(String name);

    Optional<GenreEntity> findByNameIgnoreCase(String name);

    @Query("SELECT g FROM GenreEntity g INNER JOIN g.moviesGenres m WHERE m = :movieId")
    List<GenreEntity> findByMovieId(@Param("movieId") int movieId);
}
