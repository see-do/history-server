package com.UMC.history.controller;

import com.UMC.history.DTO.UserDTO;
import com.UMC.history.service.UserService;
import com.UMC.history.util.CommonResponse;
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
    public void userSignIn(@RequestBody UserDTO.User user){
        userService.saveUserData(user);
    }

    @PostMapping(value = "/login")
    public CommonResponse<Boolean> login(@RequestBody UserDTO.User user){
        return new CommonResponse<Boolean>(userService.login(user), HttpStatus.OK);
    }

    @GetMapping(value = "/sign/{userId}/exist")
    public CommonResponse<Boolean> checkUserId(@PathVariable String userId){
        return new CommonResponse<Boolean>(userService.checkUserId(userId), HttpStatus.OK);
    }
}
