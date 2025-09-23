package com.github.satwiksanand.quoraBackend.controllers;

import com.github.satwiksanand.quoraBackend.dto.UsersRequest;
import com.github.satwiksanand.quoraBackend.dto.UsersUpdateRequestDto;
import com.github.satwiksanand.quoraBackend.models.Users;
import com.github.satwiksanand.quoraBackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getDetail(@PathVariable("userId") UUID userId) throws Exception{
        return userService.findUser(userId);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UsersRequest newUser) throws Exception {
        return userService.createUser(newUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") UUID userId) throws Exception{
        return userService.deleteUser(userId);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable("userId") UUID userId, @RequestBody UsersUpdateRequestDto userRequest) throws Exception {
        return userService.updateUser(userId, userRequest);
    }
}
