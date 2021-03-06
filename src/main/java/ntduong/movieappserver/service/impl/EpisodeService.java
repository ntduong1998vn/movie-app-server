package ntduong.movieappserver.service.impl;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.dto.EpisodeDTO;
import ntduong.movieappserver.dto.SourceDTO;
import ntduong.movieappserver.entity.EpisodeEntity;
import ntduong.movieappserver.entity.EpisodeId;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.entity.SourceEntity;
import ntduong.movieappserver.exception.EntityAlreadyExistException;
import ntduong.movieappserver.exception.EntityNotFoundException;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.mapper.EpisodeMapper;
import ntduong.movieappserver.repository.EpisodeRepository;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.service.IEpisodeService;
import ntduong.movieappserver.service.IMovieService;
import ntduong.movieappserver.service.ISourceService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpisodeService implements IEpisodeService {
    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;
    private final ISourceService sourceService;
    private final MovieRepository movieRepository;

    @Override
    public void add(EpisodeDTO episodeDTO) {
        Optional<EpisodeEntity> entityOptional = episodeRepository.findById(new EpisodeId(episodeDTO.getEpisodeId(), episodeDTO.getMovieId()));
        if (entityOptional.isPresent())
            throw new EntityAlreadyExistException("Episode", "episodeId & movieId",
                    String.format("{%d,%d}", episodeDTO.getEpisodeId(), episodeDTO.getMovieId()));
        else {
            Movie movie = movieRepository.findById(episodeDTO.getMovieId())
                    .orElseThrow(() -> new EntityNotFoundException("Movie", "Id", episodeDTO.getMovieId()));
            // Thêm tập mới
            EpisodeEntity newEpisodeEntity = episodeMapper.toEntity(episodeDTO);
            episodeRepository.save(newEpisodeEntity);
            // Thêm danh sách link
            sourceService.addAll(episodeDTO.getMovieId(), episodeDTO.getEpisodeId(), episodeDTO.getSources());
        }
    }

    @Override
    public void delete(List<Integer> episodeList) {

    }

    @Override
    public void deleteByMovieId(int movieId) {
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByMovieId(movieId);
        if (CollectionUtils.isEmpty(episodeEntityList)) {
            for (EpisodeEntity element : episodeEntityList) {
//                sourceRepository.deleteByEpisodeIdAndMovieId(element.getEpisodeId().getEpisodeId(), movieId);
            }
            episodeRepository.deleteByMovieId(movieId);
        }
    }

    @Override
    public List<EpisodeDTO> findByMovieId(int movieId) {
        List<EpisodeEntity> episodeEntityList = episodeRepository.findByMovieId(movieId);
        if (episodeEntityList.size() > 0)
            return episodeMapper.toDto(episodeEntityList);
        else throw new ResourceNotFoundException("Episode", "movieId", movieId);
    }

    @Override
    public EpisodeDTO findByEpisodeId(int episodeId, int movieId) {
        // if don't have entity matched then throw exception
        EpisodeEntity episodeEntity = episodeRepository.findById(new EpisodeId(episodeId, movieId))
                .orElseThrow(() -> new EntityNotFoundException("Episode", "EpisodeId & MovieId", "{" + episodeId + "," + movieId + "}"));
        EpisodeDTO episodeDTO = episodeMapper.toDto(episodeEntity);
        episodeDTO.setSources(sourceService.findByEpisodeIdAndMovieId(episodeId, movieId));
        return episodeDTO;
    }

    @Override
    public void addAll(List<EpisodeDTO> episodeDTOList) {
        if (!episodeDTOList.isEmpty()) {
            for (EpisodeDTO episodeDTO : episodeDTOList) {
                this.add(episodeDTO);
            }
        }
    }

    @Override
    public List<EpisodeDTO> getAll(int movieId) {
        if (movieRepository.existsById(movieId)) {
            List<EpisodeDTO> episodeDTOList = episodeMapper.toDto(episodeRepository.findByMovieId(movieId));
            for (EpisodeDTO episodeDTO : episodeDTOList) {
                List<SourceDTO> sourceDTOList = sourceService.findByEpisodeIdAndMovieId(episodeDTO.getEpisodeId(), movieId);
                episodeDTO.setSources(sourceDTOList);
            }
            return episodeDTOList;
        } else throw new EntityNotFoundException("Movie", "Id", movieId);
    }
}
