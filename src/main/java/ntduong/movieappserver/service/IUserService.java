package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.UserDTO;
import ntduong.movieappserver.entity.UserEntity;
import ntduong.movieappserver.payload.request.SignUpRequest;

public interface IUserService {

    UserDTO create(SignUpRequest signUpRequest);

}
