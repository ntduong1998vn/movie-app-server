/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.mapper;


import java.util.List;

/**
 * @param <S> Source - Entity
 * @param <D> Destination - DTO
 */
public interface EntityMapper<S, D> {
    S toEntity(D dto);

    D toDto(S entity);

    List<S> toEntity(List<D> list);

    List<D> toDto(List<S> list);
}
