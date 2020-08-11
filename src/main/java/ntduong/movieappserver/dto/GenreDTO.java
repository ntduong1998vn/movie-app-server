package ntduong.movieappserver.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {
    int id;
    @NotBlank(message = "Tên thể loại không được để trống")
    @Size(max = 45)
    String name;
}
