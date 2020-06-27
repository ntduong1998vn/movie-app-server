package ntduong.movieappserver;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.config.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

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

//        }
    }
}
