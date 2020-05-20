package ntduong.movieappserver.repository;

import ntduong.movieappserver.model.Episode;
import ntduong.movieappserver.model.EpisodeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, EpisodeId> {

}
