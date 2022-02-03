package com.UMC.history.controller;

import com.UMC.history.DTO.UserDTO;
import com.UMC.history.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/sign")
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "user submitted")
    public void userSignIn(@RequestBody UserDTO.User user){
        userService.saveUserData(user);
    }
}
