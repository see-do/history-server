package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.strongEntity.PostEntity;
<<<<<<< HEAD
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
=======
import com.UMC.history.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
>>>>>>> main
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
<<<<<<< HEAD
=======
@Getter
>>>>>>> main
@NoArgsConstructor
@Table(name="image")
public class ImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgIdx;

    @ManyToOne
    @JoinColumn(name = "postIdx")
<<<<<<< HEAD
    private PostEntity postIdx;

    @Column(nullable = false)
    private String imgUrl;

    @Column(length = 10)
    private String status;

}
=======
    private PostEntity post;

    @Column(nullable = false, length = 2048)
    private String imgUrl;


    @Builder
    public ImageEntity(PostEntity post, String imgUrl, String status){
        this.post = post;
        this.imgUrl = imgUrl;
    }

}
>>>>>>> main
