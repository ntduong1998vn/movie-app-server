package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.CommentDTO;
import ntduong.movieappserver.model.Comment;
import ntduong.movieappserver.repository.CommentRepository;
import ntduong.movieappserver.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService {

    private CommentRepository repository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.repository = commentRepository;
    }

    @Override
    public Page<CommentDTO> findByMovieId(int movieId, int currentPage, int pageSize) {
        return repository.findCommentByMovieComment_Id(movieId, PageRequest.of(currentPage,pageSize));
    }

    @Override
    public Page<CommentDTO> findByMovieIdAndUserId(int movieId, int userId, int currentPage, int pageSize) {
        return repository.findCommentByMovieComment_IdAndUserComment_Id(movieId,userId,PageRequest.of(currentPage,pageSize));
    }

    @Override
    public List<Comment> findByUserId(int userId) {
        return null;
    }

}
