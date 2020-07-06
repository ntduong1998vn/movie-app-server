package ntduong.movieappserver.service.impl;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.dto.EpisodeDTO;
import ntduong.movieappserver.entity.EpisodeEntity;
import ntduong.movieappserver.repository.EpisodeRepository;
import ntduong.movieappserver.repository.SourceRepository;
import ntduong.movieappserver.service.IEpisodeService;
import org.apache.commons.lang3.ArrayUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpisodeService implements IEpisodeService {
    private final EpisodeRepository episodeRepository;
    private final SourceRepository sourceRepository;
    private final ModelMapper modelMapper;

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
                sourceRepository.deleteByEpisodeIdAndMovieId(element.getEpisodeId().getEpisodeId(), movieId);
            }
            episodeRepository.deleteByMovieId(movieId);
        }
    }

    @Override
    public List<EpisodeDTO> findByMovieId(int movieId) {
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByMovieId(movieId);
        return episodeEntityList.stream()
                .map(episodeEntity -> modelMapper.typeMap(EpisodeEntity.class, EpisodeDTO.class)
                        .addMappings(mapper -> mapper.skip(EpisodeDTO::setSources))
                        .map(episodeEntity)
                )
                .collect(Collectors.toList());
    }

}
