/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.mapper;

import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.GenreEntity;
import ntduong.movieappserver.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface MovieMapper extends EntityMapper<Movie, MovieDTO> {

    @Mappings({
            @Mapping(target = "characters", ignore = true),
            @Mapping(target = "episodes", ignore = true),
    })
    MovieDTO toDto(Movie entity);

    @Mappings({
            @Mapping(target = "movieComments",ignore = true),
            @Mapping(target = "episodes",ignore = true),
            @Mapping(target = "characters",ignore = true),
    })
    Movie toEntity(MovieDTO dto);

    @Named("genres")
    default GenreDTO genreEntity2GenreDTO(GenreEntity genreEntity) {
        return GenreDTO.builder()
                .id(genreEntity.getId())
                .name(genreEntity.getName())
                .build();
    }
}
