package ntduong.movieappserver.repository.custom;

import ntduong.movieappserver.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional(rollbackFor = Exception.class)
public class MovieRepositoryImpl implements MovieRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Movie create(Movie movie) {
        entityManager.merge(movie);
        return movie;
    }

//    @Override
//    public List<Movie> searchMovie(List<SearchCriteria> params) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
//        Root<Movie> r = query.from(Movie.class);
//
//        Predicate predicate = builder.conjunction();
//
//        MovieSearchQueryCriteriaConsumer searchConsumer = new MovieSearchQueryCriteriaConsumer(predicate, builder, r);
//
//        params.forEach(searchConsumer);
//        predicate = searchConsumer.getPredicate();
//        query.where(predicate);
//
//        return entityManager.createQuery(query).getResultList();
//    }


//    @Override
//    public Movie findById(int movieId) {
////        Query q = this.entityManager.createQuery("select m " +
////                "from Movie m left join fetch m.genres g " +
////                "left join fetch m.episodes " +
////                "where m.id = :id");
////        q.setParameter("id",movieId);
////        return (Movie) q.getSingleResult();
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
//        Root<Movie> root = query.from(Movie.class);
//        Fetch<Movie,Genre> genreFetch = root.fetch("genres",JoinType.LEFT);
//        Fetch<Movie,Episode> episodeFetch = root.fetch("episodes",JoinType.LEFT);
////        Fetch<Episode,Source> sourceFetch = episodeFetch.fetch("sources",JoinType.LEFT);
//        Predicate condition = builder.equal(root.get("id"), movieId);
//        query
//                .select(root)
//                .where(condition);
//        TypedQuery<Movie> typedQuery = entityManager.createQuery(query);
//        return (Movie) typedQuery.getSingleResult();
//    }

    @Override
    public Page<Movie> findByGenreId(int genreId, Pageable pageRequest) {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Movie> query = builder.createQuery(Movie.class);
//        Root<Movie> root = query.from(Movie.class);
//        Join<Movie,Genre> relativeGenre = root.join("reletiveGenre",JoinType.LEFT);
//        relativeGenre.on(
//                builder.
//        )
        return null;
    }


}
