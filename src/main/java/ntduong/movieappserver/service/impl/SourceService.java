/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.SourceDTO;
import ntduong.movieappserver.entity.SourceEntity;
import ntduong.movieappserver.mapper.SourceMapper;
import ntduong.movieappserver.repository.SourceRepository;
import ntduong.movieappserver.service.ISourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService implements ISourceService {

    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private SourceMapper sourceMapper;

    @Override
    public List<SourceDTO> findByEpisodeIdAndMovieId(int episodeId,int movieId) {
        List<SourceEntity> sourceList = sourceRepository.findByEpisodeIdAndMovieId(episodeId, movieId);
        return sourceMapper.toDto(sourceList);
    }

}
