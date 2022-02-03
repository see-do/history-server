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

    public boolean login(@RequestBody UserDTO.User user){
        UserEntity findUser = userRepository.findByUserId(user.getId());
        if(findUser == null){
            return false;
        }
        if(!findUser.getPassword().equals(user.getPassword())){
            return false;
        }return true;
    }

    public boolean checkUserId(String userId){
        return userRepository.existsByUserId(userId);
    }
}
