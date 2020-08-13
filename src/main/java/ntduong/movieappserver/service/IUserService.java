package ntduong.movieappserver.service;

import ntduong.movieappserver.dto.UserDTO;
import ntduong.movieappserver.payload.form.ChangePasswordForm;
import ntduong.movieappserver.payload.request.SignUpRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    UserDTO create(SignUpRequest signUpRequest);

    UserDTO findById(int userId);

    Page<UserDTO> getUserList(String keyword, Pageable pageable);

    void update(UserDTO userDTO);

    void updateRoleAndStatus(UserDTO userDTO);

    void changePassword(int userId,ChangePasswordForm changePasswordForm) throws Exception;

    void updateVipAndSendMail(int userId);
}
