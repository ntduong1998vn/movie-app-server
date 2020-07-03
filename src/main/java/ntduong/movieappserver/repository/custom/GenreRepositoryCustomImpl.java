package ntduong.movieappserver.repository.custom;

import ntduong.movieappserver.entity.GenreEntity;
import ntduong.movieappserver.entity.Movie;
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

        GenreEntity genreEntity = entityManager.find(GenreEntity.class, genreId);

        if(genreEntity !=null){
            Set<Movie> lsMovie = genreEntity.getMoviesGenres();

            for (Movie movie : lsMovie) {
                movie.getGenres().remove(genreEntity);
            }
            genreEntity.getMoviesGenres().clear();
            entityManager.remove(genreEntity);
        }


    }


}
