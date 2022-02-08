package com.UMC.history.service;

import com.UMC.history.DTO.UserDTO;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.repository.PostRepository;
import com.UMC.history.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;

        this.passwordEncoder=passwordEncoder;
    }


    public void saveUserData(@RequestBody UserDTO.User user){
        //encode 는 패스워드를 암호화 할 때 사용
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); // password를 다시 setting하기 위해 UserEntity에 @Setter추가
        System.out.println(encodedPassword);
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getId())
                .nickName(user.getNickName())
                .password(user.getPassword())
                .build();
        userRepository.save(userEntity);
    }

    public boolean nickNameExist(String userId) {
        return userRepository.existsByNickName(userId);
    }

    public Boolean changeNickName(UserDTO.User user) {
        UserEntity hasUserId = userRepository.findByUserId(user.getId());// jwt
        if(hasUserId!=null){
            hasUserId.changeNickName(user.getNickName());
            userRepository.save(hasUserId);
            return true;
        }else return false;
    }

    public boolean login(@RequestBody UserDTO.User user){
      
        UserEntity findUser = userRepository.findByUserId(user.getId());
        if(findUser == null){
            return false;
        }

        if(!passwordEncoder.matches(user.getPassword(), findUser.getPassword())){ // 그냥 받아온 password를 넣으면 알아서 암호화해서 비교함.
            return false;
        }return true;
    }
    
    public boolean checkUserId(String userId){
        return userRepository.existsByUserId(userId);
    }
    
}
