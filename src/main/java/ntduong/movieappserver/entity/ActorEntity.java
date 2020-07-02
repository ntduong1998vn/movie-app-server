package ntduong.movieappserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "actors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 50, unique = true)
    private String name;
    private String avatar;
    @Column(length = 50)
    private String nation;

    @OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
    List<CharacterEntity> characters;

}
