package com.UMC.history.entity.strongEntity;

import com.UMC.history.entity.Embedded.FollowerEmbeddedId;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "follower")
public class FollowerEntity {

    @EmbeddedId
    private FollowerEmbeddedId followerEmbeddedId;



}