package ntduong.movieappserver;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.config.AppProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties(AppProperties.class)
@EntityScan(basePackageClasses = {
        MovieAppServerApplication.class,
        Jsr310JpaConverters.class
})
public class MovieAppServerApplication  {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieAppServerApplication.class, args);
    }

}
