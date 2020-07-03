package ntduong.movieappserver.service;


import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.entity.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface IGenreService {

    // Get all genre
    List<GenreEntity> findAll();

    boolean delete(int id);

    GenreEntity update(GenreDTO updateGenre);

    Optional<GenreEntity> findById(int id);

    List<GenreEntity> findByName(String name);

    boolean create(GenreDTO genreDTO);

    List<GenreDTO> findByMovieId(int movieId);
}
