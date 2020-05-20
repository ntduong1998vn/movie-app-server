package ntduong.movieappserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "genres_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    Set<Genre> genres;

    @OneToMany(mappedBy = "movieComment",fetch = FetchType.LAZY,orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Comment> movieComments;

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY,orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    Set<Episode> episodes;

    public void addGenre(Genre genre) {
        this.genres.add(genre);
        genre.getMoviesGenres().add(this);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
        genre.getMoviesGenres().remove(this);
    }

}
