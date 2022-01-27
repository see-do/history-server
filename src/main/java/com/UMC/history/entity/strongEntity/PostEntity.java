package com.UMC.history.entity.strongEntity;

import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class PostEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIdx;

    @ManyToOne
    @JoinColumn(name = "userIdx", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "categoryIdx", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 500)
    private String contents;

    @Column(columnDefinition = "int default 0")
    private int totalLike;

    @Column(columnDefinition = "int default 0")
    private int totalClick;

    @Column(length = 10, columnDefinition = "char(10) default 'ACTIVE'")
    private String status;

}
