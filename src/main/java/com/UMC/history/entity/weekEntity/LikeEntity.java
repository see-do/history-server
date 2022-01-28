package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="Like")
public class LikeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeIdx;

    @ManyToOne
    @JoinColumn(name="userIdx")
    private UserEntity userIdx;

    @ManyToOne
    @JoinColumn(name="postIdx")
    private PostEntity postIdx;

    @Column(length = 10)
    private String status;

}
