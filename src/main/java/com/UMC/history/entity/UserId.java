package com.UMC.history.entity;

import com.UMC.history.entity.strongEntity.UserEntity;

import java.io.Serializable;

public class UserId  implements Serializable {
    private UserEntity userIdx1; // Follower.userIdx1 매핑
    private UserEntity userIdx2; // Follower.userIdx2 매핑

    public UserId(){};
    public UserId(UserEntity user1,UserEntity user2){
        this.userIdx1=user1;
        this.userIdx2=user2;
    }
}
