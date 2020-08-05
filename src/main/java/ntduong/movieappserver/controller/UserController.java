/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ntduong.movieappserver.dto.UserDTO;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.security.CurrentUser;
import ntduong.movieappserver.security.UserPrincipal;
import ntduong.movieappserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

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
    public Page<UserDTO> getUserList(@RequestParam(defaultValue = "",required = false) String keyword,
                                     Pageable pageable) {
        return userService.getUserList(keyword,pageable);
    }

    @ApiOperation("Update Role and Status User")
    @PutMapping("/")
    public ApiResponse<String> update(@RequestBody UserDTO userDTO){
        userService.updateRoleAndStatus(userDTO);

        return new ApiResponse<>(HttpStatus.OK,"Cập nhật thành công");
    }

    @ApiOperation("GET USER BY ID")
    @GetMapping("/{userId}")
    public UserDTO findUserById(@PathVariable int userId){
        return userService.findById(userId);
    }
}
