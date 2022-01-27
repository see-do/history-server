package com.UMC.history.entity.strongEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class PostEntity {

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

    @Column(nullable = false, columnDefinition = "int default 0")
    private int totalLike;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int totalClick;

    @Column(nullable = false, length = 10, columnDefinition = "char(10) default 'active'")
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP) // -> ERD 내용 추가
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP) // -> ERD 내용 추가
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updateAt;
}
