package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.entity.FavoriteEntity;
import ntduong.movieappserver.repository.FavoriteRepository;
import ntduong.movieappserver.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService implements IFavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void add(FavoriteEntity favoriteEntity){
    }

    @Override
    public void delete(List<Integer> deleteList) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteByMovieId(int movieId) {

    }

    @Override
    public void deleteByUserId(int movieId) {

    }
}
