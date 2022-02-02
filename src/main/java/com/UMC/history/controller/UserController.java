package com.UMC.history.controller;

import com.UMC.history.DTO.UserDTO;
import com.UMC.history.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign")
    public void userSignIn(@RequestBody UserDTO.User user){
        userService.saveUserData(user);
    }
}
