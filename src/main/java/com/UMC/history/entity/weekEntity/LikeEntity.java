package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
import com.UMC.history.util.CategoryEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name="love")
public class LikeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeIdx;

    @ManyToOne
    @JoinColumn(name="userIdx")
    private UserEntity user;
    //json에서 확인을 편하게 하고자 변경 -> 닷시 userIdx로 변경 시, Repository, DTO에도 변경을 해줘야 함

    @ManyToOne
    @JoinColumn(name="postIdx")
    private PostEntity post;

    @Builder
    public LikeEntity(UserEntity user, PostEntity post) {
        this.user = user;
        this.post = post;
    }
}