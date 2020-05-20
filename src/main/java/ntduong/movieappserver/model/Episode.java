package ntduong.movieappserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "episodes")
@Data
public class Episode implements Serializable {

    private static final long serialVersionUID = 2297444104600285400L;

    @EmbeddedId
    private EpisodeId episodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movie_id")
    @JoinColumn(name = "movie_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Movie movie;

    @OneToMany(mappedBy = "episode",fetch = FetchType.LAZY,orphanRemoval = true)
    Set<Source> sources;
}