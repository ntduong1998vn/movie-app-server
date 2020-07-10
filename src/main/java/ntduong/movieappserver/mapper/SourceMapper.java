/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.mapper;

import ntduong.movieappserver.dto.SourceDTO;
import ntduong.movieappserver.entity.SourceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper extends EntityMapper<SourceEntity, SourceDTO> {

}
