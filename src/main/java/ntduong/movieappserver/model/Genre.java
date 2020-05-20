package ntduong.movieappserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Entity
@Table(name = "genres")
@Data
public class Genre implements Serializable {

    private static final long serialVersionUID = 6028372223525640170L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Movie> moviesGenres ;

    public void addMovie(Movie movie){
        this.moviesGenres.add(movie);
        movie.getGenres().add(this);
    }

    public void removeMovie(Movie movie){
        this.moviesGenres.remove(movie);
        movie.getGenres().remove(this);
    }



}
