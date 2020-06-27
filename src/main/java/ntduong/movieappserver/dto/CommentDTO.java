package ntduong.movieappserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO implements Serializable {
    private int id;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("movie_id")
    private int movieId;
    private String username;
    private String avatar;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy hh:mm")
    private LocalDateTime createAt = LocalDateTime.now();
}
