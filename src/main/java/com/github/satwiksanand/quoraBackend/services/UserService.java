package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.dto.UsersRequest;
import com.github.satwiksanand.quoraBackend.exception.IllegalUserArgumentException;
import com.github.satwiksanand.quoraBackend.exception.UserAlreadyExistsException;
import com.github.satwiksanand.quoraBackend.models.UserModel;
import com.github.satwiksanand.quoraBackend.repositories.UserRepository;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<?> createUser(UsersRequest userDto) throws Exception {
        if(userDto.getUserEmail() == null || userDto.getUserEmail().isBlank()){
            throw new IllegalUserArgumentException("Email is Required!");
        }
        if(userRepository.existsByUserEmail(userDto.getUserEmail())){
            throw new UserAlreadyExistsException("Email: " + userDto.getUserEmail() + " is already in use!");
        }
        UserModel newUser = UserModel.builder().userEmail(userDto.getUserEmail())
                .userName(userDto.getUserName())
                .profileImage(userDto.getProfileImage())
                .userDescription(userDto.getUserDescription())
                .build();
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
