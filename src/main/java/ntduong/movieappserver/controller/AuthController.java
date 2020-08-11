package ntduong.movieappserver.controller;

import lombok.RequiredArgsConstructor;
import ntduong.movieappserver.dto.UserDTO;
import ntduong.movieappserver.payload.ApiResponse;
import ntduong.movieappserver.dto.AuthResponse;
import ntduong.movieappserver.payload.request.LoginRequest;
import ntduong.movieappserver.payload.request.SignUpRequest;
import ntduong.movieappserver.security.CurrentUser;
import ntduong.movieappserver.security.UserPrincipal;
import ntduong.movieappserver.service.impl.UserService;
import ntduong.movieappserver.util.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken((UserPrincipal) authentication.getPrincipal());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody SignUpRequest signUpRequest) {
        UserDTO result = userService.create(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
//                .path("/api/user/{username}")
                .path("/api/user/me")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse<>(HttpStatus.CREATED, "Tạo thành công"));
    }

    @GetMapping("/authorities")
    @PreAuthorize("hasRole('USER') or hasRole('USER_VIP') or hasRole('ADMIN')")
    public Collection<? extends GrantedAuthority> getRoles(@CurrentUser UserPrincipal userPrincipal) {
        return userPrincipal.getAuthorities();
    }

}
