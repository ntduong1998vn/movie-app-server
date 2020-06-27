package ntduong.movieappserver.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {

    public static String ROLE_USER = "ROLE_USER";
    public static String ROLE_ADMIN = "ROLE_ADMIN";
    public static String ROLE_USER_VIP = "ROLE_USER_VIP";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @Length(max = 15)
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;
}
