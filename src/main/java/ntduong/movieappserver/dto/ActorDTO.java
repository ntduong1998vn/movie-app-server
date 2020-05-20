package ntduong.movieappserver.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActorDTO {
    private int id;
    private String name;
    private String character;

    public ActorDTO() {
    }

    public ActorDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ActorDTO(int id, String name, String character) {
        this.id = id;
        this.name = name;
        this.character = character;
    }
}
