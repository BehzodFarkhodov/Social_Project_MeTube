package org.example.authservice.service.service.user;




import org.example.authservice.domain.entity.UserEntity;
import org.example.authservice.domain.request.LoginDTO;
import org.example.authservice.domain.request.UserRequest;
import org.example.authservice.domain.response.JwtResponse;
import org.example.authservice.domain.response.UserResponse;
import org.example.authservice.exception.BaseException;


import java.util.List;
import java.util.UUID;

public interface UserService {
    String saveUser(UserRequest userRequest);
    UserResponse updateUser(UUID id, UserRequest userRequest);
    void deleteUser(UUID id);
    UserResponse findById(UUID id);
    List<UserResponse>getAllUsers();
    JwtResponse login(LoginDTO loginDTO);
    String verifyEmail(String username, String code);
    JwtResponse forEmail(String email);

}
