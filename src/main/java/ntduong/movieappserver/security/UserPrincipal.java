package ntduong.movieappserver.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ntduong.movieappserver.entity.RoleEntity;
import ntduong.movieappserver.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserPrincipal implements UserDetails, OAuth2User {

    private Integer id;
    private String email;
    private String name;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isEnable;
    private Map<String, Object> attributes;

    public UserPrincipal(Integer id, String email, String name, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isEnable = true;
    }

    public UserPrincipal(Integer id, String email, String name, String username, String password, Collection<? extends GrantedAuthority> authorities,boolean isEnable) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isEnable = isEnable;
    }

    public static UserPrincipal create(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getName().name()))
                .collect(Collectors.toList());
//                authorities = AuthorityUtils.createAuthorityList(userRoles);
//                authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrincipal(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                authorities,
                !user.isDeleteFlag()
        );
    }

    public static UserPrincipal create(UserEntity user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
