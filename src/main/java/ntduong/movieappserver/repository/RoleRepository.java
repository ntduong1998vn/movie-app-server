package ntduong.movieappserver.repository;

import ntduong.movieappserver.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String role);

    List<Role> findByNameIn(List<String> roles);
}
