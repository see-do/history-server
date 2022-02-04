package com.UMC.history.service;

import com.UMC.history.DTO.UserDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.repository.PostRepository;
import com.UMC.history.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository,PostRepository postRepository){
        this.userRepository = userRepository;
        this.postRepository = postRepository;
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

    public List<PostEntity> lookUpPostList(String userId){ //List<Long>
        UserEntity userEntity = userRepository.findByUserId(userId);
        return postRepository.findByUser(userEntity);
    }

}
