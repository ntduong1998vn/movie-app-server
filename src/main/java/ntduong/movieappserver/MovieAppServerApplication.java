package ntduong.movieappserver;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.config.AppProperties;
import ntduong.movieappserver.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(AppProperties.class)
@EnableJpaAuditing
public class MovieAppServerApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieAppServerApplication.class, args);
    }


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        uploadImage.uploadImage("","","luu-diep-phi.jpg","D:\\luu-diep-phi.jpg");
//        Path destination = Paths.get("D:\\img");
//        downloadImage.downloadObject("","","test2.jpg",destination);

//        List<SearchCriteria> params =  new ArrayList<>();
//        params.add(new SearchCriteria("imdb",">","8"));
//        params.add(new SearchCriteria("title",":","Infinity"));
//        List<Movie> result = movieRepository.searchMovie(params);
//        result.forEach(movie -> System.out.println(movie.toString()));

//        Optional<Movie> result = movieRepository.findById(16);
//        Optional<User> result2 = userRepository.findById(1);
//        Optional<Genre> result3 = genreRepository.findById(10);
//        if(result.isPresent() & result2.isPresent() ){
//            Movie movie = result.get();
//            User user = result2.get();
//            Comment comment1 = new Comment();
//            comment1.setContent("Hello Dương");
//            comment1.setMovieComment(movie);
//            comment1.setUserComment(user);
//            Comment comment2 = new Comment();
//            comment2.setContent("Hello Thanh Thanh");
//            comment2.setMovieComment(movie);
//            comment2.setUserComment(user);
//            Comment comment3 = new Comment();
//            comment3.setContent("Hello Tiểu Vy");
//            comment3.setMovieComment(movie);
//            comment3.setUserComment(user);
//
//            Set<Comment> comments = new HashSet<Comment>();
//            comments.add(comment1);
//            comments.add(comment2);
//            comments.add(comment3);
//
////            movie.getMovieComments().addAll(comments);
//            movieRepository.save(movie);

//                genreRepository.deleteByGenreId(3);
//            movieRepository.deleteById(10);
//        }
    }
}
