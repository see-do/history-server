package com.UMC.history.service;

import com.UMC.history.DTO.UserDTO;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUserData(@RequestBody UserDTO.User user){
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getId())
                .nickName(user.getNickName())
                .password(user.getPassword())
                .build();
        userRepository.save(userEntity);
    }

    public boolean nickNameExist(@RequestBody UserDTO.User user) {
        return userRepository.existsByNickName(user.getNickName());
    }

    public Boolean changeNickName(UserDTO.User user) {
        UserEntity hasUserId = userRepository.findByUserId(user.getId());// jwt
        if(hasUserId!=null){
            hasUserId.changeNickName(user.getNickName());
            userRepository.save(hasUserId);
            return true;
        }else return false;
    }
}