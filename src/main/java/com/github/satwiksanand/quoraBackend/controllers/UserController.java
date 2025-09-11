package com.github.satwiksanand.quoraBackend.controllers;

import com.github.satwiksanand.quoraBackend.dto.UsersRequest;
import com.github.satwiksanand.quoraBackend.models.UserModel;
import com.github.satwiksanand.quoraBackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getDetail(){
        return "this is the details that you are looking for";
    }

    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody UsersRequest newUser) throws Exception {
        return userService.createUser(newUser);
    }
}
