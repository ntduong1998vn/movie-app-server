package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.Episode;
import ntduong.movieappserver.entity.EpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, EpisodeId> {

}
