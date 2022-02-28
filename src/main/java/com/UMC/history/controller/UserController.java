package com.UMC.history.controller;

import com.UMC.history.DTO.QuizDTO;
import com.UMC.history.DTO.TokenDTO;
import com.UMC.history.DTO.UserDTO;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.entity.weekEntity.QuizEntity;
import com.UMC.history.service.UserService;
import com.UMC.history.util.CommonResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

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

    //유저 정보 불러오기 <프로필 정보>
    @GetMapping(value = "/information")
    public CommonResponse<UserEntity> informationAboutUser(Principal principal){
        return new CommonResponse<UserEntity>(userService.informationAboutUser(principal), HttpStatus.OK);
    }

    //회원 탈퇴
    @DeleteMapping(value = "/delete/{password}")
    public CommonResponse<Boolean> deleteUser(@PathVariable ("password") String password, Principal principal){
        return new CommonResponse<Boolean>(userService.deleteUser(password, principal), HttpStatus.OK);
    }


    //퀴즈 등록 - 단 ROLE_ADMIN유저만 접근 가능
    @PostMapping("/admin/quiz/register")
    @ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Quiz Register success")
    public void registerQuiz(@RequestBody QuizDTO.Quiz quiz) {
        userService.registerQuiz(quiz);
    }

    //퀴즈 삭제 - 단 ROLE_ADMIN유저만 접근 가능
    @DeleteMapping("/admin/quiz/delete/{quizIdx}")
    public CommonResponse<Boolean> deleteQuiz(@PathVariable ("quizIdx") Long quizIdx) {
        return new CommonResponse<Boolean>(userService.deleteQuiz(quizIdx), HttpStatus.OK);
    }
}
