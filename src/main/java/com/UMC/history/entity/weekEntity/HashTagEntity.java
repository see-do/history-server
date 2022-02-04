package com.UMC.history.entity.weekEntity;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.util.BaseEntity;
<<<<<<< HEAD
=======
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
@Table(name="HashTag")
public class HashTagEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashTagIdx;

    @ManyToOne
    @JoinColumn(name="postIdx")
<<<<<<< HEAD
    private PostEntity postIdx;
=======
    private PostEntity post;
>>>>>>> main

    @Column(nullable = false)
    private String tag;

<<<<<<< HEAD
    @Column(length = 10)
    private String status;

}
=======

    @Builder
    public HashTagEntity(String tag, String status, PostEntity post){
        this.tag = tag;
        this.post = post;
    }

}
>>>>>>> main
