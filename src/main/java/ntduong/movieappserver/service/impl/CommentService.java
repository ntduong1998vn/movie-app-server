package ntduong.movieappserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import ntduong.movieappserver.dto.CommentDTO;
import ntduong.movieappserver.entity.UserEntity;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.entity.Comment;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.repository.CommentRepository;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.repository.UserRepository;
import ntduong.movieappserver.service.ICommentService;
import org.modelmapper.ModelMapper;
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

@Slf4j
@Service
public class CommentService implements ICommentService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MovieRepository movieRepository;

    @Override
    public Page<CommentDTO> findByMovieId(int movieId, int currentPage, int pageSize) {
        return commentRepository.findCommentByMovieId(movieId,
                PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC, "createAt")));
    }

    @Override
    public Page<CommentDTO> findByMovieIdAndUserId(int movieId, int userId, int currentPage, int pageSize) {
        return commentRepository.findCommentByMovieIdAndUserId(movieId, userId,
                PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC, "createAt")));
    }

    @Override
    public Page<CommentDTO> findByUserId(int userId, int currentPage, int pageSize) {
        return commentRepository.findCommentByUserId(userId,
                PageRequest.of(currentPage, pageSize, Sort.by(Sort.Direction.DESC, "createAt")));
    }

    @Override
    public void addComment(CommentDTO commentDTO) throws ValidationException {
        Optional<UserEntity> optionalUser = userRepository.findById(commentDTO.getUserId());
        Optional<Movie> optionalMovie = movieRepository.findById(commentDTO.getMovieId());
        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            Comment newComment = new Comment();
            newComment.setUserComment(optionalUser.get());
            newComment.setMovieComment(optionalMovie.get());
            newComment.setContent(commentDTO.getContent());
            newComment.setCreateAt(LocalDateTime.now());
            commentRepository.save(newComment);
        } else throw new ValidationException("UserId or MovieId is not exist.");
    }

    @Override
    public void deleteOne(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null)
            commentRepository.delete(comment);
        else throw new ResourceNotFoundException("Comment", "Id", commentId);
    }

    @Override
    public void deleteList(List<Integer> deleteList) throws ResourceNotFoundException {
        List<Comment> commentList = new ArrayList<>();
        for (Integer integer : deleteList) {
            Comment comment = commentRepository.findById(integer).orElse(null);
            if (comment != null)
                commentList.add(comment);
            else
                throw new ResourceNotFoundException("Comment", "Id", integer);
        }
        commentRepository.deleteAll(commentList);
    }

    @Override
    public void deleteByMovieId(int movieId) {
        commentRepository.deleteByMovieId(movieId);
    }
}
