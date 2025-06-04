package com.codewithmosh.store.auth;

import com.codewithmosh.store.users.UserDto;
import com.codewithmosh.store.users.UserMapper;
import com.codewithmosh.store.users.UserNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final UserMapper userMapper;
    private final AuthService authService;
    private final JwtConfig jwtConfig;


    @PostMapping("/login")
    @Operation(summary = "Login")
    public JwtResponse login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response) {

        var loginResult = authService.login(request);

        var refreshToken = loginResult.getRefreshToken().toString();
        var cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setSecure(true);
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration()); // 7d
        response.addCookie(cookie);

        var accessToken = loginResult.getAccessToken().toString();
        return new JwtResponse(accessToken);
    }

    @GetMapping("/me")
    @Operation(summary = "Get current user")
    public ResponseEntity<UserDto> me() {
        var user = authService.getCurrentUser();
        if(user == null) {
            throw new UserNotFoundException();
        }
        var userDto = userMapper.toDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token")
    public JwtResponse refresh(
            @CookieValue(value = "refreshToken") String token) {
        var accessToken = authService.refreshAccessToken(token).toString();
        return new JwtResponse(accessToken);
    }
}
