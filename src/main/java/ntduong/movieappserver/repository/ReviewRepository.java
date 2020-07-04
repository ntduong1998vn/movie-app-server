package ntduong.movieappserver.repository;

import ntduong.movieappserver.dto.ReviewDTO;
import ntduong.movieappserver.entity.ReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {

    @Query("SELECT new ntduong.movieappserver.dto.ReviewDTO(r.id,u.id,m.id,u.name,u.imageUrl,r.content,r.score,r.createAt) " +
            "FROM ReviewEntity r " +
            "INNER JOIN r.reviewUser u " +
            "INNER JOIN r.reviewMovie m " +
            "WHERE m.id = :movieId")
    Page<ReviewDTO> findByMovieId(@Param("movieId") int movieId, Pageable page);

    @Query("SELECT new ntduong.movieappserver.dto.ReviewDTO(r.id,u.id,m.id,u.name,u.imageUrl,r.content,r.score,r.createAt) " +
            "FROM ReviewEntity r " +
            "INNER JOIN r.reviewUser u " +
            "INNER JOIN r.reviewMovie m " +
            "WHERE u.id = :userId")
    Page<ReviewDTO> findByUserId(@Param("userId") int userId, Pageable page);

    @Query("SELECT new ntduong.movieappserver.dto.ReviewDTO(r.id,u.id,m.id,u.name,u.imageUrl,r.content,r.score,r.createAt) " +
            "FROM ReviewEntity r " +
            "INNER JOIN r.reviewUser u " +
            "INNER JOIN r.reviewMovie m " +
            "WHERE m.id = :movieId AND u.id = :userId")
    Page<ReviewDTO> findByMovieIdAndUserId(@Param("movieId") int movieId,
                                           @Param("userId") int userId,
                                           Pageable page);

    @Modifying
    @Query("DELETE FROM ReviewEntity r WHERE r.reviewMovie.id = :movieId")
    void deleteByMoveId(@Param("movieId") int movieId);
}
