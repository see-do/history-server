package com.UMC.history.entity.weekEntity;


import com.UMC.history.entity.Embedded.LoveEmbeddedId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "love")
public class LoveEntity {
    @EmbeddedId
    private LoveEmbeddedId loveEmbeddedId;

    @Column(nullable = false, length = 1, columnDefinition = "char(1) default 'Y'")
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP) // -> ERD 내용 추가
    @Column(updatable = false, columnDefinition = "timestamp default current_timestamp")
    private Date createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP) // -> ERD 내용 추가
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updateAt;
}