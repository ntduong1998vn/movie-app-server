package ntduong.movieappserver.service.impl;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.constant.StaticValue;
import ntduong.movieappserver.dto.FavoriteDTO;
import ntduong.movieappserver.entity.FavoriteEntity;
import ntduong.movieappserver.exception.BadRequestException;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.repository.FavoriteRepository;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.service.IFavoriteService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import static ntduong.movieappserver.repository.specification.FavoriteSpecifications.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FavoriteService implements IFavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;

    @Override
    public void add(FavoriteDTO favoriteDTO) throws BadRequestException {
        Optional<FavoriteEntity> optionalFavoriteEntity =
                favoriteRepository.findOne(Specification.where(hasUserId(favoriteDTO.getUserId())
                        .and(hasMovieId(favoriteDTO.getMovieId()))));
        if (optionalFavoriteEntity.isPresent())
            throw new BadRequestException("Đã có trong danh sách phim yêu thích.");
        else {
            FavoriteEntity favoriteEntity = new FavoriteEntity();
            favoriteEntity.setMoviesId(favoriteDTO.getMovieId());
            favoriteEntity.setUsersId(favoriteDTO.getUserId());
            favoriteEntity.setCurrentTime(favoriteDTO.getCurrentTime());
            favoriteRepository.save(favoriteEntity);
        }
    }

    @Override
    public List<FavoriteDTO> findAllByUserId(int userId) {
        List<FavoriteEntity> favoriteEntityList =
                favoriteRepository.findAll(Specification.where(hasUserId(userId)));
        if (!CollectionUtils.isEmpty(favoriteEntityList)) {
            return favoriteEntityList.stream().map(favoriteEntity -> {
                FavoriteDTO favoriteDTO = new FavoriteDTO();
                favoriteDTO.setId(favoriteEntity.getId());
                favoriteDTO.setMovieId(favoriteEntity.getMoviesId());
                favoriteDTO.setUserId(favoriteEntity.getMoviesId());
                favoriteDTO.setCurrentTime(favoriteEntity.getCurrentTime());
                favoriteDTO.setMovieTitle(StaticValue.EMPTY_STRING);
                movieRepository.findById(favoriteEntity.getMoviesId())
                        .ifPresent(movie -> favoriteDTO.setMovieTitle(movie.getTitle()));
                return favoriteDTO;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void delete(List<Integer> deleteList) throws BadRequestException {
        List<FavoriteEntity> favoriteEntityList = new ArrayList<>();
        for (int id : deleteList) {
            FavoriteEntity favoriteEntity = favoriteRepository.findById(id).orElse(null);
            if (favoriteEntity != null) favoriteEntityList.add(favoriteEntity);
            else throw new BadRequestException("FavoriteEntity", "Id", id);
        }
        favoriteRepository.deleteAll(favoriteEntityList);
    }

    @Override
    public void delete(int id) {
        FavoriteEntity favoriteEntity = favoriteRepository.findById(id).orElse(null);
        if (favoriteEntity == null)
            throw new ResourceNotFoundException("FavoriteEntity", "Id", id);
        else favoriteRepository.delete(favoriteEntity);

    }

    @Override
    public void deleteByMovieId(int movieId) {

    }

    @Override
    public void deleteByUserId(int movieId) {

    }

    @Override
    public void updateCurrentTime(FavoriteDTO favoriteDTO) {
        Optional<FavoriteEntity> optionalFavoriteEntity =
                favoriteRepository.findById(favoriteDTO.getId());
        if (optionalFavoriteEntity.isPresent()) {
            FavoriteEntity favoriteEntity = optionalFavoriteEntity.get();
            favoriteEntity.setCurrentTime(favoriteDTO.getCurrentTime());
            favoriteRepository.save(favoriteEntity);
        } else throw new ResourceNotFoundException("FavoriteEntity", "Id", favoriteDTO.getId());
    }
}
