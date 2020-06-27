package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.entity.Genre;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.service.IMovieService;
import ntduong.movieappserver.util.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ModelMapper modelMapper;

    private MovieDTO entityToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    // Skip episode property
    private MovieDTO entityToDto_NoEpisode(Movie movie) {
        modelMapper.typeMap(Movie.class, MovieDTO.class)
                .addMappings(mapper -> mapper.skip(MovieDTO::setEpisodes));
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public Page<MovieDTO> findByPage(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size)).map(new Function<Movie, MovieDTO>() {
            @Override
            public MovieDTO apply(Movie movie) {
                return entityToDto_NoEpisode(movie);
            }
        });
    }

    @Override
    public List<Movie> findByTitle(String keyword) {
        return movieRepository.findByTitleIgnoreCaseContaining(keyword);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public MovieDTO findById(int id) {
        Movie result = movieRepository.findById(id).orElse(null);
        if (result != null)
            return modelMapper.map(result, MovieDTO.class);
        else
            return null;
    }

    @Override
    public Movie create(Movie movie) {
        return movieRepository.create(movie);
    }

    @Override
    public boolean update(int movieId, MovieDTO movieDTO) {
        return false;
    }

    @Override
    public Page<MovieDTO> findByGenreId(int id, int page, int limit) {
        Page<Movie> result = movieRepository.findMovieByGenres_Id(id, PageRequest.of(page, limit));
        return result.map(new Function<Movie, MovieDTO>() {
            @Override
            public MovieDTO apply(Movie movie) {
                return entityToDto(movie);
            }
        });

    }

    @Override
    public List<MovieDTO> searchCriteria(String search) {
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
        }

        List<Movie> result = movieRepository.searchMovie(params);
        return Optional.ofNullable(result)
                .map(list -> list.stream()
                        .map(this::entityToDto)
                        .collect(Collectors.toList()))
                .orElse(null);
    }


    @Override
    public void save(MovieDTO movie, boolean isUpdate) {
        if (isUpdate) {
            Movie oldMovie = movieRepository.findById(movie.getId()).orElse(null);
            if (oldMovie == null)
                throw new ResourceNotFoundException("MOVIE", "ID", movie.getId());
            else {
                modelMapper.map(movie, oldMovie);
                movieRepository.save(oldMovie);
            }
        } else {
            Movie newMovie = new Movie();
            Set<Genre> genres = movie.getGenres();
            for (Genre genre : genres) {
                Optional<Genre> newGenre = genreRepository.findById(genre.getId());
                if (newGenre.isPresent()) {
                    newMovie.addGenre(newGenre.get());
                } else throw new ResourceNotFoundException("GENRE", "ID", genre.getId());
            }

            modelMapper.map(movie, newMovie);
            movieRepository.save(newMovie);
        }
    }


}
