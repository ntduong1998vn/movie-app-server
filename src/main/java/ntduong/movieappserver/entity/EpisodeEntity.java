package ntduong.movieappserver.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "episodes")
@Data
public class EpisodeEntity implements Serializable {

    private static final long serialVersionUID = 2297444104600285400L;

    @EmbeddedId
    private EpisodeId episodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Movie movieEpisode;
}
