package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.EpisodeDTO;
import ntduong.movieappserver.entity.EpisodeEntity;
import ntduong.movieappserver.repository.EpisodeRepository;
import ntduong.movieappserver.repository.SourceRepository;
import ntduong.movieappserver.service.IEpisodeService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class EpisodeService implements IEpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;
    @Autowired
    SourceRepository sourceRepository;

    @Override
    public void add(List<EpisodeDTO> episodeList) {

    }

    @Override
    public void delete(List<Integer> episodeList) {

    }

    @Override
    public void deleteByMovieId(int movieId) throws Exception {
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByMovieId(movieId);
        if (CollectionUtils.isEmpty(episodeEntityList)) {
            for (EpisodeEntity element : episodeEntityList) {
                sourceRepository.deleteByEpisodeIdAndMovieId(element.getEpisodeId().getEpisodeId(),movieId);
            }
            episodeRepository.deleteByMovieId(movieId);
        }
    }
}
