package ntduong.movieappserver.controller;

import ntduong.movieappserver.dto.ApiResponse;
import ntduong.movieappserver.dto.AuthResponse;
import ntduong.movieappserver.dto.request.LoginRequest;
import ntduong.movieappserver.dto.request.SignUpRequest;
import ntduong.movieappserver.exception.BadRequestException;
import ntduong.movieappserver.model.Role;
import ntduong.movieappserver.model.User;
import ntduong.movieappserver.security.CurrentUser;
import ntduong.movieappserver.security.UserPrincipal;
import ntduong.movieappserver.service.impl.UserService;
import ntduong.movieappserver.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken((UserPrincipal) authentication.getPrincipal());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody SignUpRequest signUpRequest){
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user = userService.create(user);
        if(user !=null)
            return new ApiResponse(HttpStatus.CREATED,"Tạo thành công");
        else
            throw new BadRequestException("Email đã tồn tại!");
    }

    @GetMapping("/authorities")
    @PreAuthorize("hasRole('USER') or hasRole('USER_VIP') or hasRole('ADMIN')")
    public Collection<? extends GrantedAuthority> getRoles(@CurrentUser UserPrincipal userPrincipal){
        return userPrincipal.getAuthorities();
    }


}
