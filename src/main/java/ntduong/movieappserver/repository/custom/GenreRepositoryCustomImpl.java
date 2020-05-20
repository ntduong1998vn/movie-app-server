package ntduong.movieappserver.repository.custom;

import ntduong.movieappserver.model.Genre;
import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.repository.custom.GenreRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Transactional(rollbackFor = Exception.class)
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void deleteByGenreId(int genreId) {
//        Query query = entityManager.createQuery("SELECT g FROM Genre g JOIN fetch g.moviesGenres m where g.id = :genreId");
//        query.setParameter("genreId",genreId);
//        Genre genre = (Genre) query.getSingleResult();

        Genre genre = entityManager.find(Genre.class, genreId);

        if(genre!=null){
            Set<Movie> lsMovie = genre.getMoviesGenres();

            for (Movie movie : lsMovie) {
                movie.getGenres().remove(genre);
            }
            genre.getMoviesGenres().clear();
            entityManager.remove(genre);
        }


    }


}
