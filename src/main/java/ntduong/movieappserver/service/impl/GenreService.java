package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.entity.GenreEntity;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.service.IGenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService implements IGenreService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private GenreRepository genreRepository;


    @Override
    public boolean delete(int id) {
        Optional<GenreEntity> result = genreRepository.findById(id);
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
    public GenreEntity update(GenreDTO updateGenre) {
        Optional<GenreEntity> rs = genreRepository.findById(updateGenre.getId());
        if (rs.isPresent()) {
            return genreRepository.save(modelMapper.map(updateGenre, GenreEntity.class));
        }
        return null;
    }

    @Override
    public List<GenreEntity> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<GenreEntity> findById(int id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<GenreEntity> findByName(String name) {
        return genreRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public boolean create(GenreDTO genreDTO) {
        Optional<GenreEntity> optionalGenre = genreRepository.findByNameIgnoreCase(genreDTO.getName());
        if (optionalGenre.isPresent())
            return false;
        genreRepository.save(modelMapper.map(genreDTO, GenreEntity.class));
        return true;
    }

    @Override
    public List<GenreDTO> findByMovieId(int movieId) {
        List<GenreEntity> list = genreRepository.findByMovieId(movieId);
        return list.stream()
                .map(entity -> modelMapper.map(entity, GenreDTO.class))
                .collect(Collectors.toList());
    }
}
