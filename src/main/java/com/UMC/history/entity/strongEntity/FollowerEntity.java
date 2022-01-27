package com.UMC.history.entity.strongEntity;

import com.UMC.history.entity.weekEntity.HashTag;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Embeddable
@Table(name = "follower")
public class FollowerEntity implements Serializable{
    @EmbeddedId
    private Follower follower;
}