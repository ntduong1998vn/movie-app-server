/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.SourceDTO;

import java.util.List;

public interface ISourceService {

    List<SourceDTO> findByEpisodeIdAndMovieId(int episodeId,int movieId);

    void addAll(int movieId,int episodeId,List<SourceDTO> sourceDTOList);
}
