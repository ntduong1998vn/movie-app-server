package ntduong.movieappserver.service.impl;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.dto.FavoriteDTO;
import ntduong.movieappserver.entity.FavoriteEntity;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.exception.BadRequestException;
import ntduong.movieappserver.repository.FavoriteRepository;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.service.IFavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityExistsException;

import static ntduong.movieappserver.repository.specification.FavoriteSpecifications.*;

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
                movieRepository.findById(favoriteEntity.getMoviesId())
                        .ifPresent(movie -> favoriteDTO.setMovieTitle(movie.getTitle()));
                return favoriteDTO;
            }).collect(Collectors.toList());
        }
        return null;
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
