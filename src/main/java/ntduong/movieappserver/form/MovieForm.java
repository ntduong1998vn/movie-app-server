package ntduong.movieappserver.form;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ntduong.movieappserver.dto.CharacterDTO;
import ntduong.movieappserver.dto.EpisodeDTO;
import ntduong.movieappserver.dto.GenreDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class MovieForm {
    private int id;
    private String title;
    private String quality;
    private float imdb;
    private int runtime;
    private String release_date;
    private String overview;
    private float popularity;
    private String language;
    private MultipartFile poster;
    private int view;
    private String nation;
    private int adult;
    private boolean visible;

    List<GenreDTO> genres = new ArrayList<>();
    List<CharacterDTO> characters = new ArrayList<>();
    List<EpisodeDTO> episodes = new ArrayList<>();
}
