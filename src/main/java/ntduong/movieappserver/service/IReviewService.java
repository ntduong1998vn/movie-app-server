package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.ReviewDTO;
import org.springframework.data.domain.Page;

import javax.validation.ValidationException;
import java.util.List;

public interface IReviewService {

    Page<ReviewDTO> findByMovieId(int movieId, int page, int size);

    Page<ReviewDTO> findByUserId(int userId,int page,int size);

    Page<ReviewDTO> findByMovieIdAndUserId(int movieId,int userId,int page,int size);

    void addReview(ReviewDTO ReviewDTO) throws ValidationException;

    void deleteOne(int reviewId);

    void deleteList(List<Integer> deleteList);

    void deleteByMovieId(int movieId);
}
