package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.FavoriteEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer>, JpaSpecificationExecutor<FavoriteEntity> {
    @Override
    <S extends FavoriteEntity> boolean exists(Example<S> example);


    boolean existsByUserIdAndMovieId(int movieId,int userId);
}