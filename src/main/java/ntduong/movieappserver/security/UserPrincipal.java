package ntduong.movieappserver.security;

import lombok.*;
import ntduong.movieappserver.entity.Role;
import ntduong.movieappserver.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private int userId;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(int userId, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserEntity user){
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            if ( user.getRoles() !=null) {
                String[] userRoles = user.getRoles().stream().map(Role::getName).toArray(String[]::new);
                authorities = AuthorityUtils.createAuthorityList(userRoles);
            }else{
                authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            }

            userPrincipal.setUserId(user.getId());
            userPrincipal.setEmail(user.getEmail());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
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
        return true;
    }
}
