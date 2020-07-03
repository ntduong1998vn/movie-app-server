package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate release_date;
    String overview;
    float popularity;
    String language;
    String poster;
    int view;
    String nation;
    int adult;
    boolean visible;

    public MovieDTO(int id, String title, String quality, float imdb, int runtime, LocalDate release_date, String overview, float popularity, String language, String poster, int view, String nation, int adult, boolean visible) {
        this.id = id;
        this.title = title;
        this.quality = quality;
        this.imdb = imdb;
        this.runtime = runtime;
        this.release_date = release_date;
        this.overview = overview;
        this.popularity = popularity;
        this.language = language;
        this.poster = poster;
        this.view = view;
        this.nation = nation;
        this.adult = adult;
        this.visible = visible;
    }

    List<GenreDTO> genres = new ArrayList<>();
    List<CharacterDTO> characters = new ArrayList<>();
    List<EpisodeDTO> episodes = new ArrayList<>();
}
