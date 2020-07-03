package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.EpisodeEntity;
import ntduong.movieappserver.entity.EpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<EpisodeEntity, EpisodeId> {

    @Modifying
    @Query("DELETE FROM EpisodeEntity e WHERE e.movieEpisode.id = :movieId")
    void deleteByMovieId(@Param("movieId") int movieId);

    @Query("SELECT e FROM EpisodeEntity e WHERE e.movieEpisode.id = :movieId")
    List<EpisodeEntity> findByMovieId(@Param("movieId") int movieId);
}
