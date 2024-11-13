package org.example.authservice.repository;



import org.example.authservice.domain.entity.UserEntity;
import org.example.authservice.domain.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
/*
    @Query("select new org.example.authservice.domain.response.UserResponse(u.username,u.email)" +
            "from users u")*/
    @Query("select u from users u")
    List<UserResponse> getAllUsers();

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);


}
