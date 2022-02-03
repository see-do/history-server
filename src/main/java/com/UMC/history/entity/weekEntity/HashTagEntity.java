package com.UMC.history.entity.weekEntity;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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


    @Builder
    public HashTagEntity(String tag, String status, PostEntity post){
        this.tag = tag;
        this.post = post;
    }

}