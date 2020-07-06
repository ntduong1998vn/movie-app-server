package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.EpisodeDTO;

import java.util.List;

public interface IEpisodeService {

    void add(List<EpisodeDTO> episodeList);

    void delete(List<Integer> episodeList);

    void deleteByMovieId(int movieId) throws Exception;

    List<EpisodeDTO> findByMovieId(int movieId);
}
