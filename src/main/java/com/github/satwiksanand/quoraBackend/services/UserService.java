package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.dto.UsersRequest;
import com.github.satwiksanand.quoraBackend.exception.IllegalUserArgumentException;
import com.github.satwiksanand.quoraBackend.exception.UserAlreadyExistsException;
import com.github.satwiksanand.quoraBackend.exception.UserNotFoundException;
import com.github.satwiksanand.quoraBackend.models.Users;
import com.github.satwiksanand.quoraBackend.repositories.UserRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> createUser(UsersRequest userDto) throws Exception {
        if(userDto.getUserEmail() == null || userDto.getUserEmail().isBlank()){
            throw new IllegalUserArgumentException("Email is Required!");
        }
        if(userRepository.existsByUserEmail(userDto.getUserEmail())){
            throw new UserAlreadyExistsException("Email: " + userDto.getUserEmail() + " is already in use!");
        }
        Users newUser = Users.builder().userEmail(userDto.getUserEmail())
                .userName(userDto.getUserName())
                .profileImage(userDto.getProfileImage())
                .userDescription(userDto.getUserDescription())
                .userPassword(passwordEncoder.encode(userDto.getUserPassword()))
                .build();
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    public ResponseEntity<Void> deleteUser(UUID userId) throws Exception{
        if(!userRepository.existsById(userId)){
            throw new UserNotFoundException("user not found");
        }
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
