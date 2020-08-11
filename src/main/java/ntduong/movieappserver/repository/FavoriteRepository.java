package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.FavoriteEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer>, JpaSpecificationExecutor<FavoriteEntity> {
    Optional<FavoriteEntity> findByMoviesIdAndUsersId(int movieId,int userId);
}