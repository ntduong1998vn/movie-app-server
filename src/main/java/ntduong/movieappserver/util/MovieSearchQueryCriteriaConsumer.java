package ntduong.movieappserver.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ntduong.movieappserver.entity.Movie;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public class MovieSearchQueryCriteriaConsumer implements Consumer<SearchCriteria> {

    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root<Movie> r;

    @Override
    public void accept(SearchCriteria param) {
        switch (param.getOperation().toUpperCase()) {
            case ">":
                predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
            case "<":
                predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
            case ":":
                if (r.get(param.getKey()).getJavaType() == String.class) {
                    predicate = builder.and(predicate, builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
                } else {
                    predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
                }
        }
    }
}

//        if (param.getOperation().equalsIgnoreCase(">")) {
//            predicate = builder.and(predicate, builder.greaterThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
//        }
//        else if (param.getOperation().equalsIgnoreCase("<")) {
//            predicate = builder.and(predicate, builder.lessThanOrEqualTo(r.get(param.getKey()), param.getValue().toString()));
//        }
//        else if (param.getOperation().equalsIgnoreCase(":")) {
//            if (r.get(param.getKey()).getJavaType() == String.class) {
//                predicate = builder.and(predicate, builder.like(r.get(param.getKey()), "%" + param.getValue() + "%"));
//            }
//            else {
//                predicate = builder.and(predicate, builder.equal(r.get(param.getKey()), param.getValue()));
//            }
//        }