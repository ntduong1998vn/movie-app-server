package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.model.Genre;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GenreService implements IGenreService {

    private GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public Genre add(Genre genre) {
        return repository.save(genre);
    }

    @Override
    public boolean delete(int id) {
        Optional<Genre> result = repository.findById(id);
        if (result.isPresent()) {
            Genre genre = result.get();
//            Delete all relationships with the others movie
//            Set<Movie> movies = genre.getMovies();
//            for (Movie movie : genre.getMovies()) {
//                movie.getGenres().remove(genre);
//            }
            // Delete this genre
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Genre update(int id, GenreDTO newGenre) {
        Optional<Genre> rs = repository.findById(id);
        if (rs.isPresent()) {
            Genre updateGenre = rs.get();
            updateGenre.setName(newGenre.getName());
           return repository.save(updateGenre);
        }
        return null;
    }

    @Override
    public Page<Genre> findAll(int page, int size) {
        Pageable pageable = (Pageable) PageRequest.of(page,size);
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Genre> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Genre> findByName(String name) {
        return repository.findByNameLike(name);
    }

    @Override
    public boolean save(Genre genre) {
        Genre isExist = repository.findByNameIgnoreCase(genre.getName());
        if(isExist != null) return false;

        repository.save(genre);
        return true;
    }

}
