package ntduong.movieappserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
public class Movie implements Serializable {

    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "imdb")
    private Float imdb = 0F;

    @Column(name = "language")
    private String language = "";

    @Column(name = "nation")
    private String nation = "";

    @Column(name = "overview")
    private String overview;

    @Column(name = "popularity")
    private Float popularity = 0F;

    @Column(name = "poster")
    private String poster = "";

    @Column(name = "quality")
    private String quality = "";

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "runtime")
    private Integer runtime = 0;

    @Column(name = "title", nullable = false)
    private String title = "";

    @Column(name = "view")
    private Integer view = 0;

    @Column(name = "visible")
    private boolean visible = true;

    @Column(name = "adult")
    private Integer adult = 0;

    @ManyToMany(fetch = FetchType.EAGER,
            mappedBy = "movies",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @BatchSize(size = 6)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<GenreEntity> genres = new HashSet<>();

    @OneToMany(mappedBy = "movieComment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<Comment> movieComments = new ArrayList<>();

    @OneToMany(mappedBy = "movieEpisode",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<EpisodeEntity> episodes = new ArrayList<>();

    @OneToMany(mappedBy = "movieCharacter",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<CharacterEntity> characters = new ArrayList<>();

    public void addGenre(GenreEntity genreEntity) {
        this.genres.add(genreEntity);
        genreEntity.getMovies().add(this);
    }

    public void removeGenre(GenreEntity genreEntity) {
        this.genres.remove(genreEntity);
        genreEntity.getMovies().remove(this);
    }

}
