package org.example.authservice.service.service.user;

import lombok.RequiredArgsConstructor;


import org.example.authservice.domain.entity.UserEntity;

import org.example.authservice.domain.request.LoginDTO;
import org.example.authservice.domain.response.UserResponse;
import org.example.authservice.exception.BaseException;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.example.authservice.domain.request.UserRequest;
import org.example.authservice.domain.response.JwtResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final VerificationService verificationService;

    @Override
    public String saveUser(UserRequest userRequest) {
        if (!userRequest.getEmail().endsWith("@gmail.com")) {
            throw new BaseException("Email must be a Gmail address");
        }

        String code = generateCode();

        verificationService.sendVerificationCode(userRequest.getEmail(), code);

        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new BaseException("Username already exists");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .picturePath(userRequest.getPicturePath())
                .gmailCode(code)
                .isActive(false)
                .build();

        userRepository.save(userEntity);


        return "Verification Code Send your Email";
    }

    @Override
    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new BaseException("User not found"));
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPicturePath(userRequest.getPicturePath());
        userRepository.save(userEntity);

        return UserResponse.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(UUID id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new BaseException("User not found"));

        return UserResponse.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public JwtResponse login(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new BaseException("user not found"));

        if(userEntity.getIsActive().equals(false)){
            throw new BaseException("User is not active");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())) {
            throw new BaseException("wrong password");
        }

        return new JwtResponse(jwtService.generateAccessToken(userEntity),
                jwtService.generateRefreshToken(userEntity));
    }

    public String generateCode() {
        Random random = new Random();
        int randomCode = 1000 + random.nextInt(9000); // 1000–9999 oralig'ida tasodifiy raqam
        return String.valueOf(randomCode);
    }


    public String verifyEmail(String username, String code) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new BaseException("user not found"));
        if(userEntity.getGmailCode().equals(code)) {
            userEntity.setIsActive(true);
            userRepository.save(userEntity);
            return "Successfully verified";
        }
        throw new BaseException("Invalid email");
    }


    public JwtResponse forEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new BaseException("email not found"));
        userEntity.setIsActive(true);
        userRepository.save(userEntity);
        return new JwtResponse(jwtService.generateAccessToken(userEntity),
                jwtService.generateRefreshToken(userEntity));
    }




}
