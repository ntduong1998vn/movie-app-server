package ntduong.movieappserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ntduong.movieappserver.dto.CharacterDTO;
import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.CharacterEntity;
import ntduong.movieappserver.entity.GenreEntity;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.service.*;
import ntduong.movieappserver.util.ObjectMapperUtil;
import ntduong.movieappserver.util.SearchCriteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final IGenreService genreService;
    private final ICommentService commentService;
    private final ICharacterService characterService;
    private final IEpisodeService episodeService;
    private final IReviewService reviewService;
    private final ModelMapper modelMapper;

    // Skip association
    private MovieDTO entityToDto(Movie movie) {
        modelMapper.typeMap(Movie.class, MovieDTO.class)
                .addMappings(mapper -> mapper.skip(MovieDTO::setEpisodes))
                .addMappings(mapper -> mapper.skip(MovieDTO::setCharacters))
                .addMappings(mapper -> mapper.skip(MovieDTO::setGenres));
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    public Page<MovieDTO> findByPage(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size)).map(new Function<Movie, MovieDTO>() {
            @Override
            public MovieDTO apply(Movie movie) {
                MovieDTO result = entityToDto(movie);
                result.setGenres(ObjectMapperUtil.mapAll(movie.getGenres(), GenreDTO.class));
                return result;
            }
        });
    }

    @Override
    public List<Movie> findByTitle(String keyword) {
        return movieRepository.findByTitleIgnoreCaseContaining(keyword);
    }

    @Override
    public void deleteById(int id) throws Exception {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            // Delete all association
            // CommentEntity , CharacterEntity , EpisodeEntity , ReviewEntity , GenreEntity
            // TODO : Write function delete favorite
            commentService.deleteByMovieId(id);
            characterService.deleteByMovieId(id);
            episodeService.deleteByMovieId(id);
            reviewService.deleteByMovieId(id);
            movie.getGenres().clear();
            movieRepository.delete(movie);
        } else throw new ResourceNotFoundException("Movie", "Id", id);
    }

    @Override
    public MovieDTO findById(int id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            MovieDTO result = this.entityToDto(movie);
            result.setCharacters(characterService.findByMovieId(id));
            result.setGenres(ObjectMapperUtil.mapAll(movie.getGenres(), GenreDTO.class));
            result.setEpisodes(episodeService.findByMovieId(id));
            return result;
        }
        return null;
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
                MovieDTO result = entityToDto(movie);
                result.setGenres(ObjectMapperUtil.mapAll(movie.getGenres(), GenreDTO.class));
                return result;
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
            List<GenreDTO> genres = movie.getGenres();
            for (GenreDTO genre : genres) {
                Optional<GenreEntity> newGenre = genreRepository.findById(genre.getId());
                if (newGenre.isPresent()) {
                    newMovie.addGenre(newGenre.get());
                } else throw new ResourceNotFoundException("GENRE", "ID", genre.getId());
            }

            modelMapper.map(movie, newMovie);
            movieRepository.save(newMovie);
        }
    }


}
