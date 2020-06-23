package ntduong.movieappserver.repository;

import ntduong.movieappserver.dto.CommentDTO;
import ntduong.movieappserver.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select new ntduong.movieappserver.dto.CommentDTO(c.id,u.id,u.name,u.imageUrl,c.content) " +
            "from Comment c inner join c.userComment u " +
            "where c.movieComment.id = :movieId")
    Page<CommentDTO> findCommentByMovieComment_Id(int movieId, Pageable page);

    List<Comment> findCommentByUserComment_Id(int userId);

    @Query("select new ntduong.movieappserver.dto.CommentDTO(c.id,u.id,u.name,u.imageUrl,c.content) " +
            "from Comment c " +
            "join c.movieComment m " +
            "join c.userComment u " +
            "where m.id = :commentId and u.id = :userId")
    Page<CommentDTO> findCommentByMovieComment_IdAndUserComment_Id(int commentId,int userId,Pageable page);

}
