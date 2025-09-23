package com.github.satwiksanand.quoraBackend.services;

import com.github.satwiksanand.quoraBackend.dto.UsersRequest;
import com.github.satwiksanand.quoraBackend.dto.UsersUpdateRequestDto;
import com.github.satwiksanand.quoraBackend.exception.IllegalUserArgumentException;
import com.github.satwiksanand.quoraBackend.exception.UserAlreadyExistsException;
import com.github.satwiksanand.quoraBackend.exception.EntityNotFoundException;
import com.github.satwiksanand.quoraBackend.models.Users;
import com.github.satwiksanand.quoraBackend.repositories.UserRepository;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //function to filter out property names from a dto which are null
    private String[] getNullPropertyNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();
        Set<String> result = new HashSet<>();
        for(PropertyDescriptor pd : propertyDescriptors){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                result.add(pd.getName());
            }
        }
//        for(String which : result){
//            System.out.println(which);
//        }
//        System.out.println("divider start ---------------------- ************ -------------------- divider ends");
        return result.toArray(new String[0]);
    }

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
            throw new EntityNotFoundException("user not found");
        }
        userRepository.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Users> findUser(UUID userId) throws Exception{
        Optional<Users> user = userRepository.findById(userId);
        if(user.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        throw new EntityNotFoundException("user not found!");
    }

    public ResponseEntity<Users> updateUser(UUID userId, UsersUpdateRequestDto userRequest) throws Exception{
        //avoiding tightly coupling here
        Optional<Users> user = userRepository.findById(userId);
        if(user.isPresent()){
            BeanUtils.copyProperties(userRequest, user.get(), getNullPropertyNames(userRequest));
            userRepository.save(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        throw new EntityNotFoundException("user not found");
    }
}
