package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    @JsonProperty("release_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate releaseDate;
    String overview;
    float popularity;
    String language;
    String poster;
    int view;
    String nation;
    int adult;
    boolean visible;

    List<GenreDTO> genres = new ArrayList<>();
    List<CharacterDTO> characters = new ArrayList<>();
    List<EpisodeDTO> episodes = new ArrayList<>();
}
