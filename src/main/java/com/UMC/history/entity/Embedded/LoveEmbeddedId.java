package com.UMC.history.entity.Embedded;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Embeddable
public class LoveEmbeddedId implements Serializable {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loveIdx;

    @ManyToOne
    @JoinColumn(name = "postIdx", nullable = false)
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "userIdx", nullable = false)
    private UserEntity user;
}
