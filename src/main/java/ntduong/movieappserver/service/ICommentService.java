package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.CommentDTO;
import ntduong.movieappserver.model.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICommentService {

    public Page<CommentDTO> findByMovieId(int movieId, int currentPage,int pageSize);

    public List<Comment> findByUserId(int userId);

    public Page<CommentDTO> findByMovieIdAndUserId(int movieId,int userId,int currentPage,int pageSize);
}
