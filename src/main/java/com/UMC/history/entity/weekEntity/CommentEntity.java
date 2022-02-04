package com.UMC.history.entity.weekEntity;

<<<<<<< HEAD
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
=======
import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
import lombok.Builder;
>>>>>>> main
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="comment")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentIdx;

    @ManyToOne
    @JoinColumn(name = "postIdx")
<<<<<<< HEAD
    private PostEntity postIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private UserEntity userIdx;
=======
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private UserEntity user;
>>>>>>> main

    @Column(nullable = false)
    private String contents;

<<<<<<< HEAD
    @Column(length = 10)
    private String status;

=======
    @Builder
    public CommentEntity(PostEntity post, UserEntity user, String contents){
        this.post = post;
        this.user = user;
        this.contents = contents;
    }
>>>>>>> main
}
