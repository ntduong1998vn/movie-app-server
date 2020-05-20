package ntduong.movieappserver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CastRequest {
    @NotNull
    String name;
    @NotNull
    String profile_path;
}
