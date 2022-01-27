package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name="image")
public class ImageEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgIdx;

    @ManyToOne
    @JoinColumn(name = "postIdx")
    private PostEntity postIdx;

    @Column(nullable = false)
    private String imgUrl;

    @Column(length = 10)
    private String status;

}
