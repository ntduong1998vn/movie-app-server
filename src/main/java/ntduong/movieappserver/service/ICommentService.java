package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.CommentDTO;
import org.springframework.data.domain.Page;

import javax.validation.ValidationException;
import java.util.List;

public interface ICommentService {

    Page<CommentDTO> findByMovieId(int movieId, int currentPage,int pageSize);

    Page<CommentDTO> findByUserId(int userId,int currentPage,int pageSize);

    Page<CommentDTO> findByMovieIdAndUserId(int movieId,int userId,int currentPage,int pageSize);

    void addComment(CommentDTO commentDTO) throws ValidationException;

    void deleteOne(int commentId);

    void deleteList(List<Integer> deleteList);

    void deleteByMovieId(int movieId);
}
