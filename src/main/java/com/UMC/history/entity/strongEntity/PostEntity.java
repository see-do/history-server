package com.UMC.history.entity.strongEntity;


import com.UMC.history.entity.weekEntity.CommentEntity;
import com.UMC.history.entity.weekEntity.HashTagEntity;
import com.UMC.history.entity.weekEntity.ImageEntity;
import com.UMC.history.entity.weekEntity.LikeEntity;
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
public class PostEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIdx;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

//    @ManyToOne
//    @JoinColumn(name = "categoryIdx")
//    private CategoryEntity category;

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 500)
    private String contents;

    @Column(columnDefinition = "integer default 0")
    private Integer totalLike;

    @Column(columnDefinition = "integer default 0")
    private Integer totalClick;

    @Column(columnDefinition = "integer default 0")
    private Integer totalComment;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<HashTagEntity> hashTags = new ArrayList<>();

//    @OneToMany(mappedBy = "post", orphanRemoval = true)
//    private List<CommentEntity> comments = new ArrayList<>();

//    @OneToMany(mappedBy = "post", orphanRemoval = true)
//    private List<LikeEntity> likes = new ArrayList<>();

    @Builder
    public PostEntity(UserEntity user, CategoryEnum category, String title, String contents, Integer totalLike, Integer totalClick, Integer totalComment, List<ImageEntity> images) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.totalClick = totalClick;
        this.totalLike = totalLike;
        this.totalComment = totalComment;
        this.images = images;
    }

    public void createComment(int totalComment) {this.totalComment += totalComment;}
    public void createLike(int totalLike) {this.totalLike +=totalLike;}
}
