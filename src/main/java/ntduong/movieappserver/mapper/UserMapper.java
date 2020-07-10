/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.mapper;

import ntduong.movieappserver.dto.RoleDTO;
import ntduong.movieappserver.dto.UserDTO;
import ntduong.movieappserver.entity.RoleEntity;
import ntduong.movieappserver.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserEntity, UserDTO> {
    List<RoleDTO> toRoleEntityToRoleDTO(Set<RoleEntity> roles);
}
