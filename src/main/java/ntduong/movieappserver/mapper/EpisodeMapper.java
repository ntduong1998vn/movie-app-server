/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.mapper;

import ntduong.movieappserver.dto.EpisodeDTO;
import ntduong.movieappserver.dto.SourceDTO;
import ntduong.movieappserver.entity.EpisodeEntity;
import ntduong.movieappserver.entity.SourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EpisodeMapper extends EntityMapper<EpisodeEntity, EpisodeDTO> {

    @Override
    @Mappings({
            @Mapping(target = "episodeId.episodeId", source = "episodeId"),
            @Mapping(target = "episodeId.movieId", source = "movieId"),
            @Mapping(target = "movieEpisode.id", source = "movieId")
    })
    EpisodeEntity toEntity(EpisodeDTO dto);

    @Override
    @Mappings({
            @Mapping(target = "episodeId", source = "episodeId.episodeId"),
            @Mapping(target = "movieId", source = "episodeId.movieId"),
    })
    EpisodeDTO toDto(EpisodeEntity entity);


    @Named("sources")
    default SourceDTO sourceEntityToSourceDTO(SourceEntity sourceEntity) {
        SourceDTO dto = SourceDTO.builder()
                .id(sourceEntity.getId())
                .label(sourceEntity.getLabel())
                .resolution(sourceEntity.getResolution())
                .server(sourceEntity.getServer().toString())
                .src(sourceEntity.getSrc())
                .build();
        return dto;
    }
}
