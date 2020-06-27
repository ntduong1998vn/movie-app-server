package ntduong.movieappserver.util;

import ntduong.movieappserver.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieSpecificationsBuilder {
    private List<SearchCriteria> params;

    public MovieSpecificationsBuilder(){
        params = new ArrayList<SearchCriteria>();
    }

    public MovieSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Movie> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(MovieSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

//        for (int i = 1; i < params.size(); i++) {
//            result = params.get(i)
//                    .isOrPredicate()
//                    ? Specification.where(result)
//                    .or(specs.get(i))
//                    : Specification.where(result)
//                    .and(specs.get(i));
//        }
        return result;
    }

}
