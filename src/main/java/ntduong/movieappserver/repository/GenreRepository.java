package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.Genre;
import ntduong.movieappserver.repository.custom.GenreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>, GenreRepositoryCustom {

    List<Genre> findByNameContainsIgnoreCase(String name);

    Optional<Genre> findByNameIgnoreCase(String name);
}
