package com.UMC.history.entity.weekEntity;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import javax.persistence.*;

@NoArgsConstructor
@Embeddable
public class Liked implements Serializable {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "postIdx", nullable = false)
    private PostEntity post;
}