package com.UMC.history.entity.strongEntity;


import com.UMC.history.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(nullable = false, length = 100)
    private String nickName;

    @Column(nullable = false, length = 100)
    private String userid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String profileImgUrl;

    @Column(columnDefinition = "boolean default false")
    private Boolean lockScreen;

    @Column(columnDefinition = "boolean default false")
    private Boolean autoLoginFlag; // boolean 으로 생각되어 String -> boolean 으로 변경

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
