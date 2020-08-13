package ntduong.movieappserver.service.impl;

import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ntduong.movieappserver.constant.StaticValue;
import ntduong.movieappserver.dto.GenreDTO;
import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.entity.GenreEntity;
import ntduong.movieappserver.exception.EntityNotFoundException;
import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.mapper.MovieMapper;
import ntduong.movieappserver.payload.form.MovieForm;
import ntduong.movieappserver.repository.GenreRepository;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.service.*;
import ntduong.movieappserver.util.DateFormatUtil;
import ntduong.movieappserver.util.ImageUtil;
import ntduong.movieappserver.util.ObjectMapperUtil;
import ntduong.movieappserver.util.searchMovie.MovieSpecificationsBuilder;
import ntduong.movieappserver.util.searchMovie.SearchOperation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
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
    private final ImageUtil imageUtil;
    private final DateFormatUtil dateFormat;
    private final MovieMapper movieMapper;

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
//            result.setEpisodes(episodeService.findByMovieId(id));
            return result;
        }
        return null;
    }

    @Override
    public boolean update(int movieId, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(movieDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieDTO.getId()));


        movieRepository.save(movie);
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
    public Page<MovieDTO> searchCriteria(String search, int page, int size) {
        MovieSpecificationsBuilder builder = new MovieSpecificationsBuilder();
        String operationSetExper = Joiner.on('|').join(SearchOperation.SIMPLE_OPERATION_SET);
//        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
//        Matcher matcher = pattern.matcher(search + ",");
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<Movie> spec = builder.build();
        Page<Movie> result = movieRepository.findAll(spec, PageRequest.of(page, size));
        return result.map(movieMapper::toDto);
    }

    @Override
    public void save(MovieForm movieForm, boolean isUpdate) throws IllegalArgumentException, IOException {
        Movie movie;
        if (isUpdate) {
            movie = movieRepository.findById(movieForm.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("MOVIE", "ID", movieForm.getId()));
            // Update poster
            MultipartFile imgFile = movieForm.getPoster();
            if (imgFile != null) {
                if (Objects.equals(imgFile.getContentType(), StaticValue.JPEG) ||
                        Objects.equals(imgFile.getContentType(), StaticValue.PNG)) {
                    if (imageUtil.deleteImage(StaticValue.POSTER, movie.getPoster())) {
                        imageUtil.uploadImage(StaticValue.POSTER, imgFile.getOriginalFilename(), imgFile.getContentType(), imgFile.getInputStream());
                        movie.setPoster(imgFile.getOriginalFilename());
                    } else {
                        log.info("Xóa ảnh không thành công");
                    }
                }
            }

            // Edit genre association
            updateGenreRelationship(movie, movieForm.getGenres());
        } else {
            movie = new Movie();
            List<GenreDTO> genres = movieForm.getGenres();
            List<Integer> genreIdList = genres
                    .stream()
                    .map(GenreDTO::getId)
                    .collect(Collectors.toList());
            List<GenreEntity> genreEntityList = genreRepository.findAllById(genreIdList);
            for (GenreEntity genreEntity : genreEntityList) {
                movie.addGenre(genreEntity);
            }
            // Store image to image and save file name to database
            MultipartFile imgFile = movieForm.getPoster();
            if (imgFile != null && !imgFile.isEmpty()) {
                imageUtil.uploadImage(StaticValue.POSTER, imgFile.getOriginalFilename(), imgFile.getContentType(), imgFile.getInputStream());
                movie.setPoster(imgFile.getOriginalFilename());
            }
        }

        // Set basic information of movie
        movie.setTitle(movieForm.getTitle());
        movie.setQuality(movieForm.getQuality());
        movie.setRuntime(movieForm.getRuntime());
        movie.setImdb(movieForm.getImdb());
        movie.setReleaseDate(dateFormat.stringToLocalDate(movieForm.getRelease_date(), StaticValue.YYYYMMDD));
        movie.setOverview(movieForm.getOverview());
        movie.setPopularity(movieForm.getPopularity());
        movie.setLanguage(movieForm.getLanguage());
        movie.setView(movieForm.getView());
        movie.setNation(movieForm.getNation());
        movie.setAdult(movieForm.getAdult());
        movie.setVisible(movieForm.isVisible());
        // Save
        movieRepository.save(movie);
    }

    @Override
    public void updateStatus(int movieId, boolean value) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie", "Id", movieId));
        movie.setVisible(value);
        movieRepository.save(movie);
    }

    private void updateGenreRelationship(Movie movie, List<GenreDTO> updateList) throws IllegalArgumentException {
        List<Integer> genreIdList = updateList
                .stream()
                .map(GenreDTO::getId)
                .collect(Collectors.toList());
        List<GenreEntity> updateGenreList = genreRepository.findAllById(genreIdList);

        Set<GenreEntity> currentSet = movie.getGenres();
        // Remove genre if list updatedList not contain
        Iterator<GenreEntity> each = currentSet.iterator();
        while (each.hasNext()) {
            GenreEntity genreEntity = each.next();
            if (!updateGenreList.contains(genreEntity)) {
                genreEntity.removeMovie(movie);
            }
        }

        // Add genre if currentGenreList not contain
        for (GenreEntity genreEntity : updateGenreList) {
            if (!currentSet.contains(genreEntity)) {
                movie.addGenre(genreEntity);
            }
        }
    }

}
