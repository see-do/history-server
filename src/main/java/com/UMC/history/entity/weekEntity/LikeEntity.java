package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
<<<<<<< HEAD
import lombok.Generated;
=======
>>>>>>> main
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
<<<<<<< HEAD
@Table(name="Like")
=======
@Table(name="love")
>>>>>>> main
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

<<<<<<< HEAD
    @Column(length = 10)
    private String status;

}
=======
}
>>>>>>> main
