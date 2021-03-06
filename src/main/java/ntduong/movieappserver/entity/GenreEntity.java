package ntduong.movieappserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "genres")
@Data
public class GenreEntity implements Serializable {

    private static final long serialVersionUID = 6028372223525640170L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "genres_movies",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies ;

    public void addMovie(Movie movie){
        this.movies.add(movie);
        movie.getGenres().add(this);
    }

    public void removeMovie(Movie movie){
        this.movies.remove(movie);
        movie.getGenres().remove(this);
    }

}
