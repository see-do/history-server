package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.Embedded.CommentEmbeddedId;
import com.UMC.history.entity.Embedded.LoveEmbeddedId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {
    @EmbeddedId
    private CommentEmbeddedId contentEmbeddedId;

    @Column(nullable = false, length = 10, columnDefinition = "char(10) default 'active'")
    private String status;

    @Column(nullable = false, length = 500)
    private String contents;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP) // -> ERD 내용 추가
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP) // -> ERD 내용 추가
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updateAt;
}