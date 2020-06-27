package ntduong.movieappserver.repository;

import ntduong.movieappserver.dto.CommentDTO;
import ntduong.movieappserver.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT new ntduong.movieappserver.dto.CommentDTO(c.id,u.id,m.id,u.name,u.imageUrl,c.content,c.createAt) " +
            "FROM Comment c " +
            "INNER JOIN c.userComment u " +
            "INNER JOIN c.movieComment m " +
            "WHERE m.id = :movieId")
    Page<CommentDTO> findCommentByMovieId(@Param("movieId") int movieId,
                                          Pageable page);

    @Query("SELECT new ntduong.movieappserver.dto.CommentDTO(c.id,u.id,m.id,u.name,u.imageUrl,c.content,c.createAt) " +
            "FROM Comment c " +
            "INNER JOIN c.userComment u " +
            "INNER JOIN c.movieComment m " +
            "WHERE c.userComment.id = :userId")
    Page<CommentDTO> findCommentByUserId(@Param("userId") int userId, Pageable page);

    @Query("SELECT new ntduong.movieappserver.dto.CommentDTO(c.id,u.id,m.id,u.name,u.imageUrl,c.content,c.createAt) " +
            "FROM Comment c " +
            "INNER JOIN c.movieComment m " +
            "INNER JOIN c.userComment u " +
            "WHERE m.id = :movieId AND u.id = :userId")
    Page<CommentDTO> findCommentByMovieIdAndUserId(@Param("movieId") int movieId,
                                                     @Param("userId") int userId,
                                                     Pageable page);

}
