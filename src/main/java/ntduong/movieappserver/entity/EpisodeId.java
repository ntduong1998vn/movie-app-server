package ntduong.movieappserver.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EpisodeId implements Serializable {

    @Column(name ="episode_id")
    private int episodeId;

    @Column(name = "movie_id")
    private int movieId;
}
