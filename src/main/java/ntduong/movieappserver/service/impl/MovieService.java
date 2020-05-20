package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.dto.MovieDTO;
import ntduong.movieappserver.model.Movie;
import ntduong.movieappserver.repository.MovieRepository;
import ntduong.movieappserver.service.IMovieService;
import ntduong.movieappserver.util.SearchCriteria;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MovieService implements IMovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
    private MovieRepository movieRepository;
    private ModelMapper modelMapper;
    private DateFormat dateFormat;

    @Autowired
    public MovieService(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    }

    private MovieDTO entityToDto(Movie movie){
        Converter<Date,String> converterReleaseDate = context -> context.getSource() == null ? "1-1-1990" : dateFormat.format(context.getSource());
        modelMapper.typeMap(Movie.class,MovieDTO.class)
                .addMappings(mapper->mapper.using(converterReleaseDate)
                        .map(Movie::getRelease_date,MovieDTO::setRelease_date));
        return modelMapper.map(movie,MovieDTO.class);
    }

    @Override
    public Page<MovieDTO> findAll(int page, int size) {
        return movieRepository.findAll(PageRequest.of(page, size)).map(new Function<Movie, MovieDTO>() {
            @Override
            public MovieDTO apply(Movie movie) {
                return entityToDto(movie);
            }
        });
    }

    @Override
    public Page<Movie> getTopView(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by("views").descending());
        return movieRepository.findAll(pageable);
    }

    @Override
    public boolean addGenres(int movieId, List<Integer> addList) {
        return false;
    }

    @Override
    public boolean removeGenres(int movieId, List<Integer> deleteList) {
        return false;
    }

    @Override
    public List<Movie> findByTitle(String keyword) {
        return movieRepository.findByTitleIgnoreCaseContaining(keyword);
    }

    @Override
    public List<Movie> findByLetterBegin(String letter) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
//        Optional<Movie> result = movieRepository.findById(id);
//        if(!result.isPresent()) return false;
//        Movie movie = result.get();
//        for (Genre genre: movie.getGenres()) {
//
//        }
        return false;
    }

    @Override
    public MovieDTO findById(int id) {
        Optional<Movie> result = movieRepository.findById(id);
        if (result.isPresent()) {
            MovieDTO movieDTO = modelMapper.map(result.get(), MovieDTO.class);
            return movieDTO;
        }
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
    public Page<MovieDTO> getMoviesByGenreId(int id, int page, int limit) {
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

        List<Movie> result =  movieRepository.searchMovie(params);
        return Optional.ofNullable(result)
                        .map(list -> list.stream()
                                .map(this::entityToDto)
                                .collect(Collectors.toList()))
                        .orElse(null);
    }
//    @Override
//    public Movie save(Movie movie) {
//        return movieRepository.save(movie);
//    }
//
//    @Override
//    public boolean addGenres(int movieId, List<Integer> genreIds) {
//        List<Genre> genreList = new ArrayList<>();
//
//        Optional<Movie> result1 = movieRepository.findById(movieId);
//        // Kiem tra movie exits
//        if (!result1.isPresent()) return false;
//
//        // Lay danh sach genres
//        boolean errFlag = false;
//        for (int genreId : genreIds) {
//            Optional<Genre> temp = genreRepository.findById(genreId);
//            if (temp.isPresent())
//                genreList.add(temp.get());
//            else {
//                errFlag = true;
//                break;
//            }
//        }
//        if (errFlag) return false;
//
//        Movie movie = result1.get();
//        for (Genre genre : genreList) {
//            movie.addGenre(genre);
//        }
//        Movie result2 = movieRepository.save(movie);
//
//        return result2 != null;
//    }
//
//    @Override
//    public boolean removeGenres(int movieId, List<Integer> deleteList) {
//        // List Genre wll be deleted
//        List<Genre> genreList = new ArrayList<>();
//
//        Optional<Movie> result1 = movieRepository.findById(movieId);
//        // Check movie exits
//        if (!result1.isPresent()) return false;
//
//        // Get deleted genre list
//        boolean errFlag = false;
//        for (int genreId : deleteList) {
//            Optional<Genre> temp = genreRepository.findById(genreId);
//            if (temp.isPresent())
//                genreList.add(temp.get());
//            else {
//                errFlag = true;
//                break;
//            }
//        }
//        if (errFlag) return false;
//
//        Movie movie = result1.get();
//        for (Genre genre : genreList) {
//            movie.removeGenre(genre);
//        }
//        Movie result2 = movieRepository.save(movie);
//
//        return result2 != null;
//    }
//
//    @Override
//    @Transactional
//    public boolean update(int movieId, MovieDTO movieDTO) {
//        Optional<Movie> result = movieRepository.findById(movieId);
//        if (result.isPresent()) {
//            try {
//                Movie updateMovie = result.get();
//                updateMovie.setTitle(movieDTO.getTitle());
//                updateMovie.setRuntime(movieDTO.getRuntime());
//                updateMovie.setRelease_date(movieDTO.getRelease_date());
//                updateMovie.setOverview(movieDTO.getOverview());
//                updateMovie.setVote_average(movieDTO.getVote_average());
//                updateMovie.setProduction_countries(movieDTO.getProduction_countries());
//                updateMovie.setPopularity(movieDTO.getPopularity());
//                updateMovie.setLanguage(movieDTO.getLanguage());
//                updateMovie.setPoster_path(movieDTO.getPoster_path());
//                updateMovie.setBackdrop_path(movieDTO.getBackdrop_path());
//                updateMovie.setViews(movieDTO.getViews());
//                movieRepository.save(updateMovie);
//                // Update relationship with Genre entity
//                boolean updateGenresRelationship = updateGenresRelationship(movieId, movieDTO.getGenres());
//                boolean updateCastersRelationship = updateCastersRelationship(movieId, movieDTO.getCasters());
//
//                return updateGenresRelationship && updateCastersRelationship;
//            } catch (Exception e) {
//                logger.error(e.getMessage());
//                return false;
//            }
//        }
//        return false;
//    }
//
//    @Transactional
//    private boolean updateGenresRelationship(int movieId, Set<GenreDTO> genreUpdateList) {
//        Optional<Movie> result = movieRepository.findById(movieId);
//        if (!result.isPresent()) return false;
//        Set<Genre> movieGenreList = result.get().getGenres();
//
//        List<Integer> addList = genreUpdateList.stream()
//                .filter(genre -> movieGenreList.stream().noneMatch(x -> x.getId() == genre.getId()))
//                .map(GenreDTO::getId)
//                .collect(Collectors.toList());
//
//        List<Integer> removeList = movieGenreList.stream()
//                .filter(genre -> genreUpdateList.stream().noneMatch(x -> x.getId() == genre.getId()))
//                .map(Genre::getId)
//                .collect(Collectors.toList());
//
//        try {
//            addGenres(movieId, addList);
//            removeGenres(movieId, removeList);
//            return true;
//        } catch (DataAccessException e) {
//            logger.error(e.getMessage());
//            return false;
//        }
//    }
//
//    @Transactional
//    private boolean updateCastersRelationship(int movieId, Set<ActorDTO> castUpdateList) {
//        Optional<Movie> result = movieRepository.findById(movieId);
//        if (!result.isPresent()) return false;
//        List<ActorDTO> movieCastList = castService.getCharactersByMovieId(movieId);
//
//        List<Integer> addList = castUpdateList.stream()
//                .filter(cast -> movieCastList.stream().noneMatch(x -> x.getId() == cast.getId()))
//                .map(ActorDTO::getId)
//                .collect(Collectors.toList());
//
//        List<Integer> removeList = movieCastList.stream()
//                .filter(cast -> castUpdateList.stream().noneMatch(x -> x.getId() == cast.getId()))
//                .map(ActorDTO::getId)
//                .collect(Collectors.toList());
//
//        try {
//            // Xoá diễn viên trong bộ phim
//            for (int castId : removeList) {
//                movieCastRepository.deleteByMovieCastKeyCastId(castId);
//            }
//
//            // Thêm diễn viên mới
//            String character = "Diễn viên";
//            Movie movie = result.get();
//            List<Cast> castAddList = new ArrayList<>();
//
//            for (int castId : addList) {
//                Optional<Cast> result1 = castService.findByCastId(castId);
//                if (result1.isPresent())
//                    castAddList.add(result1.get());
//            }
//
//            for (Cast cast : castAddList) {
//                MovieCast caster = new MovieCast();
//                caster.setCharacter(character + " " + cast.getId());
//                caster.setMovie_cast(movie);
//                caster.setCast_movie(cast);
//                movieCastRepository.save(caster);
//            }
//            return true;
//        } catch (DataAccessException e) {
//            logger.error(e.getMessage());
//            return false;
//        }
//    }
//
//    @Override
//    public List<Movie> findByTitle(String keyword) {
//        return movieRepository.findByTitleName(keyword);
//    }
//
//    @Override
//    public List<Movie> findByLetterBegin(String letter) {
//        if (letter.contains("0-9"))
//            return movieRepository.findByNumberBegin();
//        return movieRepository.findByLetterBegin(letter);
//    }
//
}
