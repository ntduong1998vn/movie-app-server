package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    int id;
    String title;
    String quality;
    float imdb;
    int runtime;
    String release_date;
    String overview;
    float popularity;
    String language;
    String poster;
    int view;
    String nation;
    int adult;

    Set<GenreDTO> genres = new HashSet<>();
}
