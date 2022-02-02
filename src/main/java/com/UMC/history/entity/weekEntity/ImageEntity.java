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
@Table(name="image")
public class ImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgIdx;

    @ManyToOne
    @JoinColumn(name = "postIdx")
    private PostEntity post;

    @Column(nullable = false, length = 2048)
    private String imgUrl;

    @Column(length = 10)
    private String status;

    @Builder
    public ImageEntity(PostEntity post, String imgUrl, String status){
        this.post = post;
        this.imgUrl = imgUrl;
        this.status = status;
    }

}