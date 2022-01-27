package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.UserId;
import com.UMC.history.entity.strongEntity.UserEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@IdClass(UserId.class)
@Table(name="Follower")
public class FollowerEntity {

    @Id
    @ManyToOne
    @JoinColumn(name="to_user")
    private UserEntity userIdx1;

    @Id
    @ManyToOne
    @JoinColumn(name="from_user")
    private UserEntity userIdx2;

}

