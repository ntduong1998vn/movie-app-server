package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.FavoriteDTO;

import java.util.List;

public interface IFavoriteService {
    void add(FavoriteDTO favoriteDTO);

    List<FavoriteDTO> findAllByUserId(int userId);
    void delete(List<Integer> deleteList);

    void delete(int id);

    void deleteByMovieId(int movieId);

    void deleteByUserId(int movieId);
}
