package ntduong.movieappserver.repository;

import ntduong.movieappserver.constant.RoleNameEnum;
import ntduong.movieappserver.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(RoleNameEnum role);

    List<RoleEntity> findByNameIn(List<String> roles);
}
