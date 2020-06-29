package ntduong.movieappserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
public class Movie implements Serializable {

    private static final long serialVersionUID = -297553281792804396L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String quality;
    float imdb;
    int runtime;
    Date release_date;
    @Lob
    String overview;
    float popularity;
    String language;
    String poster;
    int view;
    String nation;
    int adult;
    @Column(columnDefinition = "tinyint(4) default 1")
    boolean visible = true;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "moviesGenres",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Genre> genres;

    @OneToMany(mappedBy = "movieComment",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<Comment> movieComments;

    @OneToMany(mappedBy = "movieEpisode",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    List<Episode> episodes;

    @OneToMany(mappedBy = "movieCharacter",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<CharacterEntity> characters;

    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getMoviesGenres().add(this);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getMoviesGenres().remove(this);
    }

}
