package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.model.Genre;
import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.service.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GenreService implements IGenreService {

    private GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public boolean delete(int id) {
        Optional<Genre> result = genreRepository.findById(id);
        if (result.isPresent()) {
//            Genre deletedGenre = result.get();
//            Delete all relationships with the others movie
//            Set<Movie> movies = deletedGenre.getMoviesGenres();
//            List<Movie> movies = new ArrayList<>(deletedGenre.getMoviesGenres());
//            for (Movie movie : movies) {
//                movie.removeGenre(deletedGenre);
//            }
            // Delete this genre
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Genre update(int id, GenreDTO newGenre) {
        Optional<Genre> rs = genreRepository.findById(id);
        if (rs.isPresent()) {
            Genre updateGenre = rs.get();
            updateGenre.setName(newGenre.getName());
           return genreRepository.save(updateGenre);
        }
        return null;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(int id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findByName(String name) {
        return genreRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public boolean create(Genre genre) {
        Genre isExist = genreRepository.findByNameIgnoreCase(genre.getName());
        if(isExist != null) return false;
        genreRepository.save(genre);
        return true;
    }

}
