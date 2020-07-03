package ntduong.movieappserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Table(name = "favorites")
@Entity
@Data
public class FavoriteEntity implements Serializable {
    private static final long serialVersionUID = 9086801233295612962L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Column(name = "movies_id", nullable = false)
    private Integer moviesId;

    @Column(name = "users_id", nullable = false)
    private Integer usersId;

    @Column(name = "current_time")
    private LocalTime currentTime;

}