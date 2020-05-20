package ntduong.movieappserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "sources")
@Data
public class Source implements Serializable {

    private static final long serialVersionUID = 3614187567235181697L;

    public enum SourceType{
        YOUTUBE,
        JWPLAYER,
        DRIVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "movie_id",referencedColumnName = "movie_id"),
            @JoinColumn(name = "episode_id",referencedColumnName = "episode_id")
    })
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Episode episode;

    @NotNull
    @Enumerated
    @Column(columnDefinition = "smallint")
    SourceType type;

    String src;
    String label;
}
