package com.UMC.history.entity.strongEntity;


import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.util.BaseEntity;
import com.UMC.history.util.CategoryEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
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

    @Column(length = 10)
    private String status;

    @Builder
    public PostEntity(UserEntity user, CategoryEnum category, String title, String contents, Integer totalLike, Integer totalClick, String status) {
        this.user = user;
        this.category = category;
        this.title = title;
        this.contents = contents;
        this.totalClick = totalClick;
        this.totalLike = totalLike;
    }
}
