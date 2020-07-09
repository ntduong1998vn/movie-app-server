/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.mapper;

import ntduong.movieappserver.dto.ActorDTO;
import ntduong.movieappserver.entity.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ActorMapper extends EntityMapper<ActorEntity, ActorDTO> {
    @Mappings({
            @Mapping(target = "id", source = "dto.id")
    })
    ActorEntity toEntity(ActorDTO dto);

    ActorDTO toDto(ActorEntity entity);
}
