package ntduong.movieappserver.service;


import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.model.Genre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IGenreService {

    // Get all genre
    public List<Genre> findAll();

    public boolean delete(int id);

    public Genre update(int id, GenreDTO newGenre);

    public Optional<Genre> findById(int id);

    public List<Genre> findByName(String name);

    public boolean create(Genre genre);
}
