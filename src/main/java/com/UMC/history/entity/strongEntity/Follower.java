package com.UMC.history.entity.strongEntity;

import com.UMC.history.entity.strongEntity.PostEntity;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import javax.persistence.*;

@NoArgsConstructor
@Embeddable
public class Follower implements Serializable {

    @ManyToOne
    @JoinColumn(name = "to_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "from_user", nullable = false)
    private UserEntity user1;
}
