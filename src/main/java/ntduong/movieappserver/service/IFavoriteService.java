package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.FavoriteDTO;
import ntduong.movieappserver.exception.BadRequestException;

import java.util.List;

public interface IFavoriteService {
    void add(FavoriteDTO favoriteDTO) throws BadRequestException;

    List<FavoriteDTO> findAllByUserId(int userId);

    void delete(List<Integer> deleteList);

    void delete(int id);

    void deleteByMovieId(int movieId);

    void deleteByUserId(int movieId);
}
