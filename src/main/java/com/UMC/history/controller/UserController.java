package com.UMC.history.controller;

import com.UMC.history.DTO.TokenDTO;
import com.UMC.history.DTO.UserDTO;
import com.UMC.history.service.UserService;
import com.UMC.history.util.CommonResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping(value = "/sign/{nickName}/nickNameExist") //닉네임 중복체크
    public CommonResponse<Boolean> nickNameExist(@PathVariable String nickName){
        return new CommonResponse<Boolean> (userService.nickNameExist(nickName), HttpStatus.OK);
    }

    @PatchMapping(value = "/changeNickName") //닉네임 변경
    public CommonResponse<Boolean> changeNickName(@RequestBody UserDTO.User user){
        return new CommonResponse<Boolean>(userService.changeNickName(user), HttpStatus.OK);
    }

    @PostMapping(value = "/login") //로그인
    public CommonResponse<TokenDTO> login(@RequestBody UserDTO.User user) {
        //로그인 예외처리
        TokenDTO token=userService.login(user);
        //아이디 오류시
        if (token.getGrantType()=="Id Error"){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid ID");
        }else if(token.getGrantType()=="Password Error"){ //비밀번호 오류시
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid Password");
        }
        return new CommonResponse<TokenDTO>(token, HttpStatus.OK);
    }
    
    @GetMapping(value = "/sign/{userId}/exist") //id존재여부 확인
    public CommonResponse<Boolean> checkUserId(@PathVariable String userId){
        return new CommonResponse<Boolean>(userService.checkUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    public CommonResponse<TokenDTO> reissue(@RequestBody TokenDTO tokenRequestDto) { //RequestBody로 Access Token + Refresh Token를 받는다.
        return new CommonResponse<TokenDTO>(userService.reissue(tokenRequestDto), HttpStatus.OK);
    }
}
