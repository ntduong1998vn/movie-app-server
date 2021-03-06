package ntduong.movieappserver.util.searchMovie;

import ntduong.movieappserver.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MovieSpecificationsBuilder {
    private final List<SearchCriteria> params;

    public MovieSpecificationsBuilder() {
        this.params = new ArrayList<>();
    }

    public MovieSpecificationsBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Movie> build() {
        if (params.size() == 0) {
            return null;
        }

        Specification<Movie> result = new MovieSpecification(params.get(0));

//        for (int i = 1; i < params.size(); i++) {
//            result = params.get(i).isOrPredicate()
//                    ? Specification.where(result).or(new UserSpecification(params.get(i)))
//                    : Specification.where(result).and(new UserSpecification(params.get(i)));
//        }
        return result;
    }


}
