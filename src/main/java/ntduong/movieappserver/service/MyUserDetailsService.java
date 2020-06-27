package ntduong.movieappserver.service;

import ntduong.movieappserver.exception.ResourceNotFoundException;
import ntduong.movieappserver.entity.User;
import ntduong.movieappserver.repository.UserRepository;
import ntduong.movieappserver.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found with email : " + email));
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id)
        );
        return UserPrincipal.create(user);
    }

}
