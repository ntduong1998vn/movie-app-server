package ntduong.movieappserver.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDTO{
    private int id;
    @JsonProperty("movie_name")
    private String movieTitle;
    @JsonProperty("movie_id")
    private int movieId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("current_time")
    private int currentTime = 0;
}