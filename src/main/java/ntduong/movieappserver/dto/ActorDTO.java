package ntduong.movieappserver.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorDTO {
    private int id;
    private String name;
    private String avatar;
    private String nation;
}
