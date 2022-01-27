package com.UMC.history.entity.Embedded;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@NoArgsConstructor
@Embeddable
public class FollowerEmbeddedId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "to_user", nullable = false)
    private UserEntity user1;

    @ManyToOne
    @JoinColumn(name = "from_user", nullable = false)
    private UserEntity user2;
}
