package com.UMC.history.entity.strongEntity;


<<<<<<< HEAD
import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "post")
=======
import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.weekEntity.HashTagEntity;
import com.UMC.history.entity.weekEntity.ImageEntity;
import com.UMC.history.util.BaseEntity;
import com.UMC.history.util.CategoryEnum;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
@NoArgsConstructor
@Table(name = "post")
@DynamicInsert
>>>>>>> main
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIdx;

    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "userIdx")
    private UserEntity userIdx;

    @ManyToOne
    @JoinColumn(name = "categoryIdx")
    private CategoryEntity category;
=======
    @JoinColumn(name = "userId")
    private UserEntity user;

//    @ManyToOne
//    @JoinColumn(name = "categoryIdx")
//    private CategoryEntity category;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
>>>>>>> main

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 500)
    private String contents;

<<<<<<< HEAD
    @Column(columnDefinition = "int default 0")
    private String totalLike;

    @Column(columnDefinition = "int default 0")
    private String totalClick;

    @Column(length = 10)
    private String status;
=======
    @Column(columnDefinition = "integer default 0")
    private Integer totalLike;

    @Column(columnDefinition = "integer default 0")
    private Integer totalClick;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<HashTagEntity> hashTags = new ArrayList<>();

    @Builder
    public PostEntity(UserEntity user, CategoryEnum category, String title, String contents, Integer totalLike, Integer totalClick, List<ImageEntity> images) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.totalClick = totalClick;
        this.totalLike = totalLike;
        this.images = images;
    }
>>>>>>> main
}
