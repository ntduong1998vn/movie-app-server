/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.repository;

import ntduong.movieappserver.dto.CharacterDTO;
import ntduong.movieappserver.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity,Integer> {

    @Modifying
    @Query(value = "DELETE FROM CharacterEntity c WHERE c.actor.id = :actorId")
    void deleteByActorId(@Param("actorId") int actorId);

    @Query("SELECT c FROM CharacterEntity c WHERE c.movieCharacter.id = :movieId")
    List<CharacterEntity> findByMovieId(@Param("movieId")int movieId);

    @Modifying
    @Query(value = "DELETE FROM CharacterEntity c WHERE c.movieCharacter.id = :movieId")
    void deleteByMovieId(@Param("movieId") int movieId);
}
