/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.dto.UserDTO;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.payload.form.ChangePasswordForm;
import ntduong.movieappserver.security.CurrentUser;
import ntduong.movieappserver.security.UserPrincipal;
import ntduong.movieappserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Api("User APIs")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    IUserService userService;

    @ApiOperation("GET USERDETAIL BY USERNAME")
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserDTO getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        try {
            return userService.findById(userPrincipal.getId());
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }

    @ApiOperation("GET USER LIST \n ?keyword=gcltt10&page=0&size=5&sort=id,desc")
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDTO> getUserList(@RequestParam(defaultValue = "", required = false) String keyword,
                                     Pageable pageable) {
        return userService.getUserList(keyword, pageable);
    }

    @ApiOperation("Update Role and Status User")
    @PutMapping("/")
    public ApiResponse<String> updateStatus(@RequestBody UserDTO userDTO) {
        userService.updateRoleAndStatus(userDTO);

        return new ApiResponse<>(HttpStatus.OK, "Cập nhật thành công");
    }

    @ApiOperation("Update basic info")
    @PutMapping("/update-basic-info/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> updateBasicInfor(@CurrentUser UserPrincipal userPrincipal,
                                                   @RequestBody UserDTO userDTO,
                                                   @PathVariable int userId) {
        try {
            userDTO.setId(userPrincipal.getId());
            userService.update(userDTO);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Cập nhật thành công.", HttpStatus.OK);
    }

    @ApiOperation("Update password")
    @PutMapping("/changePassword/{userId}")
    public ResponseEntity<String> updatePassword(@CurrentUser UserPrincipal userPrincipal,
                                                 @RequestBody ChangePasswordForm changePasswordForm,
                                                 @PathVariable int userId) {
        try {
            userService.changePassword(userPrincipal.getId(), changePasswordForm);
            return ResponseEntity.ok("Cập nhật mật khẩu thành công.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation("GET USER BY ID")
    @GetMapping("/{userId}")
    public UserDTO findUserById(@PathVariable int userId) {
        return userService.findById(userId);
    }

    @ApiOperation("Update User Vip")
    @GetMapping("/update-vip")
    @PreAuthorize("hasRole('USER')")
    public void updateVip(@CurrentUser UserPrincipal userPrincipal) {
        userService.updateVipAndSendMail(userPrincipal.getId());
    }

    @ApiOperation("Reset password")
    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam("email") String email) {
        userService.forgetPassword(email);
    }

    @ApiOperation("Reset password")
    @GetMapping("/changePassword")
    public String showChangePasswordPage(Locale locale, @RequestParam("token") String token) {
        userService.resetPassword(token);
        return "Mật khẩu đã được đổi thành 123456";
    }
}
