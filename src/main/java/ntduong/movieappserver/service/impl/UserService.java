package ntduong.movieappserver.service.impl;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.constant.RoleNameEnum;
import ntduong.movieappserver.constant.StaticValue.AuthProvider;
import ntduong.movieappserver.entity.RoleEntity;
import ntduong.movieappserver.entity.UserEntity;
import ntduong.movieappserver.exception.BadRequestException;
import ntduong.movieappserver.exception.EntityNotFoundException;
import ntduong.movieappserver.payload.request.SignUpRequest;
import ntduong.movieappserver.repository.RoleRepository;
import ntduong.movieappserver.repository.UserRepository;
import ntduong.movieappserver.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername()))
            throw new BadRequestException("Username is already taken!");

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email Address already in use!");
        }
//        boolean isExist = userRepository.existsByEmailIgnoreCase(signUpRequest.getEmail());
        UserEntity user = new UserEntity();
        user.setEmail(signUpRequest.getEmail());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setProvider(AuthProvider.LOCAL);

        RoleEntity userRole = roleRepository.findByName(RoleNameEnum.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("Role", "Name", "ROLE_USER"));
        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }
}
