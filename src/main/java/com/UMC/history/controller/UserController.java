package com.UMC.history.controller;

import com.UMC.history.DTO.TokenDTO;
import com.UMC.history.DTO.UserDTO;
import com.UMC.history.service.UserService;
import com.UMC.history.util.CommonResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/sign/nickNameExist") //닉네임 중복체크
    public CommonResponse<Boolean> nickNameExist(@RequestBody UserDTO.User user){
        return new CommonResponse<Boolean> (userService.nickNameExist(user), HttpStatus.OK);
    }

    @PatchMapping(value = "/changeNickName") //닉네임 변경
    public CommonResponse<Boolean> changeNickName(@RequestBody UserDTO.User user){
        return new CommonResponse<Boolean>(userService.changeNickName(user), HttpStatus.OK);
    }

    @PostMapping(value = "/login") //로그인
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO.User user) {
        return ResponseEntity.ok().body(userService.login(user));
    }
    
    @GetMapping(value = "/sign/{userId}/exist") //id존재여부 확인
    public CommonResponse<Boolean> checkUserId(@PathVariable String userId){
        return new CommonResponse<Boolean>(userService.checkUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDTO> reissue(@RequestBody TokenDTO tokenRequestDto) { //RequestBody로 Access Token + Refresh Token를 받는다.
        return ResponseEntity.ok(userService.reissue(tokenRequestDto));
    }

}
