package ntduong.movieappserver.service.impl;

import com.nimbusds.oauth2.sdk.Role;
import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.constant.RoleNameEnum;
import ntduong.movieappserver.constant.StaticValue.AuthProvider;
import ntduong.movieappserver.dto.RoleDTO;
import ntduong.movieappserver.dto.UserDTO;
import ntduong.movieappserver.entity.Movie;
import ntduong.movieappserver.entity.RoleEntity;
import ntduong.movieappserver.entity.UserEntity;
import ntduong.movieappserver.exception.BadRequestException;
import ntduong.movieappserver.exception.EntityNotFoundException;
import ntduong.movieappserver.mapper.RoleMapper;
import ntduong.movieappserver.mapper.UserMapper;
import ntduong.movieappserver.payload.request.SignUpRequest;
import ntduong.movieappserver.repository.RoleRepository;
import ntduong.movieappserver.repository.UserRepository;
import ntduong.movieappserver.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Override
    public UserDTO create(SignUpRequest signUpRequest) {
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

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO findById(int userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy userId= " + userId));
        return userMapper.toDto(userEntity);
    }

    @Override
    public Page<UserDTO> getUserList(String keyword, Pageable pageable) {
        Page<UserEntity> userEntityList;
        if (StringUtils.isEmpty(keyword))
            userEntityList = userRepository.findAll(pageable);
        else
            userEntityList = userRepository.findByUsername(keyword, pageable);
        return userEntityList.map(userEntity -> userMapper.toDto(userEntity));
    }

    @Override
    public void update(UserDTO userDTO) {

    }

    @Override
    public void updateRoleAndStatus(UserDTO userDTO) {
        UserEntity user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("User", "id", userDTO.getId()));

        List<RoleEntity> roleList = roleMapper.toEntity(userDTO.getRoles());
        user.getRoles().clear();
        user.setRoles(new HashSet<RoleEntity>(roleList));
        user.setDeleteFlag(userDTO.isDeleteFlag());
        userRepository.save(user);
    }

}
