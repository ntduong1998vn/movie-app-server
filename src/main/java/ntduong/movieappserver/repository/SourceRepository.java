package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceRepository extends JpaRepository<SourceEntity,Integer> {

    @Modifying
    @Query("DELETE FROM SourceEntity s WHERE s.episodeId = :episodeId AND s.movieId = :movieId")
    void deleteByEpisodeIdAndMovieId(@Param("episodeId") int episodeId,@Param("movieId") int movieId);

    List<SourceEntity> findByEpisodeIdAndMovieId(int episodeId,int movieId);
}
