///*
// * @Author by Nguyen Trieu Duong
// * @Email: ntduong1998vn@gmail.com
// */
//
//package ntduong.movieappserver.mapper;
//
//import ntduong.movieappserver.dto.FavoriteDTO;
//import ntduong.movieappserver.entity.FavoriteEntity;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//
//@Mapper(componentModel = "spring")
//public interface FavoriteMapper extends EntityMapper<FavoriteEntity, FavoriteDTO> {
//
//    @Mappings({
//            @Mapping(target = "id", source = "dto.id"),
//            @Mapping(target = "moviesId",source = "dto.movieId"),
//            @Mapping(target = "usersId",source = "dto.userId"),
//            @Mapping(target = "currentTime",source = "dto.currentTime")
//    })
//    FavoriteEntity toEntity(FavoriteDTO dto);
//
//    @Mappings({
//            @Mapping(target = "id", source = "entity.id"),
//            @Mapping(target = "movieId", source = "entity.moviesId"),
//            @Mapping(target = "userId", source = "entity.usersId"),
//            @Mapping(target = "currentTime", source = "entity.currentTime"),
//    })
//    FavoriteDTO toDto(FavoriteEntity entity);
//}
