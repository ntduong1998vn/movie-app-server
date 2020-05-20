package ntduong.movieappserver.service;


import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.model.Genre;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IGenreService {

    public Genre add(Genre genre);

    public boolean delete(int id);

    public Genre update(int id, GenreDTO newGenre);

    public Page<Genre> findAll(int page, int size);

    public Optional<Genre> findById(int id);

    public List<Genre> findByName(String name);

    public boolean save(Genre genre);
}
