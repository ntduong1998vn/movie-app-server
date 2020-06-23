package ntduong.movieappserver.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private int id;
    @NotNull
    private String email; // UserID or Email is unique
    @NotNull
    private int movieId;
    private String content;
}
