package ntduong.movieappserver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class GenreDTO {

    int id;
    @NotBlank(message = "Tên thể loại không được để trống")
    String name;
}
