package ntduong.movieappserver.util;

import lombok.AllArgsConstructor;
import ntduong.movieappserver.entity.Movie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class MovieSpecification implements Specification<Movie> {

    private SearchCriteria criteria;

    @Override
    public Specification<Movie> and(Specification<Movie> other) {
        return null;
    }

    @Override
    public Specification<Movie> or(Specification<Movie> other) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }


}
