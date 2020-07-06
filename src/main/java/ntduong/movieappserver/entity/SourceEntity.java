package ntduong.movieappserver.entity;

import lombok.Data;
import ntduong.movieappserver.constant.StaticValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "sources")
@Data
public class SourceEntity implements Serializable {

    private static final long serialVersionUID = 3614187567235181697L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "label",length = 20)
    private String label;

    @Column(name = "episode_id", nullable = false)
    private Integer episodeId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Enumerated
    @Column(columnDefinition = "smallint",nullable = false)
    StaticValue.SourceType server;

    @Column(name = "src")
    private String src;

    @Column(name = "resolution",length = 6)
    private String resolution;
    //    @Column(name = "server", nullable = false)
    //    private Integer server;
}
