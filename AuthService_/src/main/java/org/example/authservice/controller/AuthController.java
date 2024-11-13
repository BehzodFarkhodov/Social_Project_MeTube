package org.example.authservice.controller;

import lombok.RequiredArgsConstructor;


import org.example.authservice.domain.entity.UserEntity;
import org.example.authservice.domain.request.LoginDTO;
import org.example.authservice.domain.request.UserRequest;
import org.example.authservice.domain.response.JwtResponse;
import org.example.authservice.domain.response.UserResponse;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.JwtService;
import org.example.authservice.service.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:3001", "http://159.65.119.240:8080"})
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }


    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable UUID id) {
        return userService.findById(id);
    }


    @PutMapping("/update/{id}")
    public UserResponse updateUser(@RequestBody UserRequest userRequest,
                                   @PathVariable UUID id) {
        return userService.updateUser(id, userRequest);
    }


    @GetMapping("/verifyEmail")
    public String verfyEmail(@RequestParam String username, @RequestParam String code) {
      return userService.verifyEmail(username, code);
    }

    @GetMapping("/loginWithEmail")
    public JwtResponse loginWithEmail(@RequestParam String email) {
        return userService.forEmail(email);
    }


    @GetMapping("/token")
    public ResponseEntity<?> generateToken(OAuth2AuthenticationToken authentication) {
        OAuth2User oauth2User = authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String username = email.split("@")[0];
        if (!userRepository.existsByEmail(email)) {
            UserEntity newUser = new UserEntity();
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPassword("");
            userRepository.save(newUser);
        }


        String accessToken = jwtService.generateAccessTokenOAuth(oauth2User);
        String refreshToken = jwtService.generateRefreshTokenOAuth(oauth2User);

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken,
                "email", email,
                "username", username
        ));
    }

}
