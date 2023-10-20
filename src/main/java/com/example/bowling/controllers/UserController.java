package com.example.bowling.controllers;

import com.example.bowling.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{user}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@PathVariable("user") final String userName){
        userService.createUser(userName);
    }
}
