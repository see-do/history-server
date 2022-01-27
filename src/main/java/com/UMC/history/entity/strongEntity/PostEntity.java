package com.UMC.history.entity.strongEntity;


import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private UserEntity userIdx;

    @ManyToOne
    @JoinColumn(name = "categoryIdx")
    private CategoryEntity category;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 500)
    private String contents;

    @Column(columnDefinition = "int default 0")
    private String totalLike;

    @Column(columnDefinition = "int default 0")
    private String totalClick;

    @Column(length = 10)
    private String status;
}
