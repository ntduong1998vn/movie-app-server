package ntduong.movieappserver.service;

import ntduong.movieappserver.entity.FavoriteEntity;

import java.util.List;

public interface IFavoriteService {
    void add(FavoriteEntity favoriteEntity);

    void delete(List<Integer> deleteList);

    void delete(int id);

    void deleteByMovieId(int movieId);

    void deleteByUserId(int movieId);
}
