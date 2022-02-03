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

    @GetMapping(value = "/sign/nickNameExist") //닉네임 중복체크
    public CommonResponse<Boolean> nickNameExist(@RequestBody UserDTO.User user){
        return new CommonResponse<Boolean> (userService.nickNameExist(user), HttpStatus.OK);
    }

    @PatchMapping(value = "/changeNickName") //닉네임 변경
    public CommonResponse<Boolean> changeNickName(@RequestBody UserDTO.User user){
        return new CommonResponse<Boolean>(userService.changeNickName(user), HttpStatus.OK);
    }

}
