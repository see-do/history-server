package com.UMC.history.entity.weekEntity;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.util.BaseEntity;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="HashTag")
public class HashTagEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashTagIdx;

    @ManyToOne
    @JoinColumn(name="postIdx")
    private PostEntity post;

    @Column(nullable = false)
    private String tag;

    @Column(length = 10)
    private String status;

    @Builder
    public HashTagEntity(String tag, String status, PostEntity post){
        this.tag = tag;
        this.status = status;
        this.post = post;
    }

}