package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class EpisodeDTO {
    @JsonProperty("episode_id")
    private int episodeId;
    @JsonProperty("movie_id")
    private int movieId;

    List<SourceDTO> sources = new ArrayList<>();
}
