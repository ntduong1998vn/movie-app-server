/*
 * @Author by NguyenTrieuDuong
 */

package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<ActorEntity,Integer> {

}
