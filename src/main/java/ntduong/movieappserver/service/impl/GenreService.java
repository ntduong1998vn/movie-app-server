package ntduong.movieappserver.service.impl;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.entity.GenreEntity;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.service.IGenreService;
import ntduong.movieappserver.util.ObjectMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GenreService implements IGenreService {

    private final ModelMapper modelMapper;
    private final GenreRepository genreRepository;

    @Override
    public void delete(int id) {
        GenreEntity genreEntity = genreRepository.findById(id).orElse(null);
        if (genreEntity != null) {
//            Set<Movie> movieList = genreEntity.getMovies();
//            Iterator<Movie> value = movieList.iterator();
//            while (value.hasNext()) {
//                genreEntity.removeMovie(value.next());
//            }
            genreRepository.delete(genreEntity);
        } else throw new ResourceNotFoundException("GenreEntity", "Id", id);
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
    public List<GenreDTO> findAll() {
        return ObjectMapperUtil.mapAll(genreRepository.findAll(), GenreDTO.class);
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
