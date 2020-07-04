/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.repository.specification;

import ntduong.movieappserver.entity.FavoriteEntity;
import ntduong.movieappserver.entity.FavoriteEntity_;
import org.springframework.data.jpa.domain.Specification;

public class FavoriteSpecifications {

    public static Specification<FavoriteEntity> hasMovieId(int movieId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FavoriteEntity_.MOVIES_ID),movieId);
    }

    public static Specification<FavoriteEntity> hasUserId(int userId){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(FavoriteEntity_.USERS_ID),userId);
    }
}
