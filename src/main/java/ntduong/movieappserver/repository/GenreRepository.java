package ntduong.movieappserver.repository;

import ntduong.movieappserver.model.Genre;
import ntduong.movieappserver.repository.custom.GenreRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>, GenreRepositoryCustom {

    List<Genre> findByNameLike(String name);

    Genre findByNameIgnoreCase(String name);

}
