package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.EpisodeDTO;

import java.util.List;

public interface IEpisodeService {

    void add(EpisodeDTO episodeDTO);

    void delete(List<Integer> episodeList);

    void deleteByMovieId(int movieId) throws Exception;

    List<EpisodeDTO> findByMovieId(int movieId);

    EpisodeDTO findByEpisodeId(int episodeId,int movieId);

    void addAll(List<EpisodeDTO> episodeDTOList);

    List<EpisodeDTO> getAll(int movieId);
}
