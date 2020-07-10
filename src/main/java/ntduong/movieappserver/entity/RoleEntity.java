package ntduong.movieappserver.entity;


import lombok.*;
import ntduong.movieappserver.constant.RoleNameEnum;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

//    public static String ROLE_USER = "ROLE_USER";
//    public static String ROLE_ADMIN = "ROLE_ADMIN";
//    public static String ROLE_USER_VIP = "ROLE_USERVIP";
//    public static String ROLE_REVIEWER = "ROLE_REVIEWER";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    @NaturalId
    @Column(length = 15)
    private RoleNameEnum name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<UserEntity> users;
}
