package com.github.satwiksanand.quoraBackend.controllers;

import com.github.satwiksanand.quoraBackend.dto.UsersRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {
    @GetMapping
    private String getHome(){
        return "this is the default page that you should see instead of that weird looking error page";
    }

    @PostMapping
    private void postDetails(@RequestBody UsersRequest user){
        System.out.println(user.getName() + " " + user.getDescription() + " " + user.getId());
    }
}
