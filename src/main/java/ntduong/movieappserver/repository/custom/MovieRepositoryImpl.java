package ntduong.movieappserver.repository.custom;

import ntduong.movieappserver.model.Genre;
import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.repository.custom.MovieRepositoryCustom;
import ntduong.movieappserver.util.MovieSearchQueryCriteriaConsumer;
import ntduong.movieappserver.util.SearchCriteria;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Movie create(Movie movie) {
        entityManager.merge(movie);
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Movie> query=builder.createQuery(Movie.class);
//        Root<Movie> root = query.from(Movie.class);
        return movie;
    }

    @Override
    public List<Movie> searchMovie(List<SearchCriteria> params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
        Root<Movie> r = query.from(Movie.class);

        Predicate predicate = builder.conjunction();

        MovieSearchQueryCriteriaConsumer searchConsumer = new MovieSearchQueryCriteriaConsumer(predicate,builder,r);

        params.forEach(searchConsumer);
        predicate =  searchConsumer.getPredicate();
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteById(int movieId) {
        Movie movie = entityManager.find(Movie.class,movieId);

        if(movie !=null){
            // Delete all relationship with genre table
            for (Genre genre : movie.getGenres()) {
                genre.removeMovie(movie);
            }
            // Delete all comments
            movie.getMovieComments().clear();
            entityManager.remove(movie);
        }

    }



}
