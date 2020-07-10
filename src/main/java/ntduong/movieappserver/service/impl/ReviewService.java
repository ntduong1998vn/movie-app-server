package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.ReviewDTO;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.entity.ReviewEntity;
import ntduong.movieappserver.entity.UserEntity;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.repository.ReviewRepository;
import ntduong.movieappserver.repository.UserRepository;
import ntduong.movieappserver.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;

    @Override
    public Page<ReviewDTO> findByMovieId(int movieId, int page, int size) {
        return reviewRepository.findByMovieId(movieId,
                PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"createAt")));
    }

    @Override
    public Page<ReviewDTO> findByUserId(int userId, int page, int size) {
        return reviewRepository.findByUserId(userId,
                PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"createAt")));
    }

    @Override
    public Page<ReviewDTO> findByMovieIdAndUserId(int movieId, int userId, int page, int size) {
        return reviewRepository.findByMovieIdAndUserId(movieId,userId,
                PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"createAt")));
    }

    @Override
    public void addReview(ReviewDTO reviewDTO) throws ValidationException {
        Optional<UserEntity> optionalUser = userRepository.findById(reviewDTO.getUserId());
        Optional<Movie> optionalMovie = movieRepository.findById(reviewDTO.getMovieId());
        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            ReviewEntity reviewEntity = new ReviewEntity();
            reviewEntity.setReviewUser(optionalUser.get());
            reviewEntity.setReviewMovie(optionalMovie.get());
            reviewEntity.setContent(reviewDTO.getContent());
            reviewEntity.setCreateAt(LocalDateTime.now());
            reviewRepository.save(reviewEntity);
        } else throw new ValidationException("UserId or MovieId is not exist.");
    }

    @Override
    public void deleteOne(int reviewId) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewId).orElse(null);
        if (reviewEntity != null)
            reviewRepository.delete(reviewEntity);
        else throw new ResourceNotFoundException("Review", "Id", reviewId);
    }

    @Override
    public void deleteList(List<Integer> deleteList) {
        List<ReviewEntity> reviewEntityList = new ArrayList<>();
        for (Integer integer : deleteList) {
            ReviewEntity reviewEntity = reviewRepository.findById(integer).orElse(null);
            if (reviewEntity != null)
                reviewEntityList.add(reviewEntity);
            else
                throw new ResourceNotFoundException("Review", "Id", integer);
        }
        reviewRepository.deleteAll(reviewEntityList);
    }

    @Override
    public void deleteByMovieId(int movieId) {
        reviewRepository.deleteByMoveId(movieId);
    }
}
