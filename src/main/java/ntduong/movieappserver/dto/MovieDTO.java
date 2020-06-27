package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntduong.movieappserver.entity.Genre;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    int id;
    String title;
    String quality;
    float imdb;
    int runtime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    Date release_date;
    String overview;
    float popularity;
    String language;
    String poster;
    int view;
    String nation;
    int adult;
    boolean visible;
    Set<Genre> genres = new HashSet<>();
    Set<EpisodeDTO> episodes = new HashSet<>();
}
