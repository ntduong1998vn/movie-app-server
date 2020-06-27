package ntduong.movieappserver.service.impl;

import ntduong.movieappserver.entity.Role;
import ntduong.movieappserver.entity.User;
import ntduong.movieappserver.repository.RoleRepository;
import ntduong.movieappserver.repository.UserRepository;
import ntduong.movieappserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User create(User user) {
        boolean isExist = userRepository.existsByEmailIgnoreCase(user.getEmail());
        if(isExist)
            return null;
        else{
            user.setProvider(User.AuthProvider.LOCAL);
            List<Role> role = Arrays.asList(roleRepository.findByName(Role.ROLE_USER));
            user.setRoles(new HashSet<>(role));
            return userRepository.save(user);
        }
    }
}
