package ntduong.movieappserver.repository;

import ntduong.movieappserver.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmailIgnoreCase(String email);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    List<UserEntity> findByIdIn(List<Long> userIds);

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT u " +
            "FROM UserEntity u " +
            "WHERE u.email LIKE CONCAT('%',UPPER(:keyword),'%')")
    Page<UserEntity> findByUsername(String keyword, Pageable pageable);
}
