package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ntduong.movieappserver.model.Source;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class EpisodeDTO {
    @JsonProperty("episode_id")
    int episodeId;
    @JsonProperty("movie_id")
    int movieId;
    List<SourceDTO> sources = new ArrayList<>();
}
