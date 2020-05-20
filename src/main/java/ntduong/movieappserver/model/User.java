package ntduong.movieappserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users",uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8558959700883205704L;

    public enum AuthProvider {
        LOCAL,
        FACEBOOK,
        GOOGLE,
        GITHUB
    }

    public enum UserType{
        NORMAL,
        VIP,
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Email
    @Column(nullable = false)
    private String email;
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;
    private String avatar;

    @NotNull
    @Enumerated
    @Column(columnDefinition = "smallint")
    private AuthProvider provider;

    @NotNull
    @Enumerated
    @Column(columnDefinition = "smallint")
    private UserType type;

    private String providerId;

    @Column(name = "role", columnDefinition = "varchar(10) default 'ROLE_USER' ")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "userComment",fetch = FetchType.LAZY)
    Set<Comment> comments;
}
