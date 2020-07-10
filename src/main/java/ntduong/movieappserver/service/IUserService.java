package ntduong.movieappserver.service;

import ntduong.movieappserver.entity.UserEntity;
import ntduong.movieappserver.payload.request.SignUpRequest;

public interface IUserService {

    UserEntity create(SignUpRequest signUpRequest);

}
