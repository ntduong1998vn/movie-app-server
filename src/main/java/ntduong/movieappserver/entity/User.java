package ntduong.movieappserver.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
public class User extends BaseEntity{

    public enum AuthProvider {
        LOCAL,
        FACEBOOK,
        GOOGLE,
        GITHUB
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    private String password;
    private String avatar;
    private String providerId;
    @Column(columnDefinition = "")
    private boolean deleted;

    @NotNull
    @Enumerated
    @Column(columnDefinition = "smallint")
    private AuthProvider provider;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "userComment",fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Comment> comments;

    //    @Column(name = "role", columnDefinition = "varchar(10) default 'ROLE_USER' ")
    //    private String role;
}
