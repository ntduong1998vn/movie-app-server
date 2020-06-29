/*
 * @Author by NguyenTrieuDuong
 */

package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Integer> {
    @Query("SELECT a FROM ActorEntity a JOIN FETCH a.characters WHERE a.id = :actorId")
    Optional<ActorEntity> findById(@Param("actorId") int id);
}
