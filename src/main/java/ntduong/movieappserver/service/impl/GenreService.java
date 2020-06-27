package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.entity.Genre;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.service.IGenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService implements IGenreService {

    @Autowired
    ModelMapper modelMapper;

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
    public Genre update(GenreDTO updateGenre) {
        Optional<Genre> rs = genreRepository.findById(updateGenre.getId());
        if (rs.isPresent()) {
           return genreRepository.save(modelMapper.map(updateGenre,Genre.class));
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
    public boolean create(GenreDTO genreDTO) {
        Optional<Genre> optionalGenre = genreRepository.findByNameIgnoreCase(genreDTO.getName());
        if(optionalGenre.isPresent())
            return false;
        genreRepository.save(modelMapper.map(genreDTO,Genre.class));
        return true;
    }
}
