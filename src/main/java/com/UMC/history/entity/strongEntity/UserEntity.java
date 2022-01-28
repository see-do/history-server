package com.UMC.history.entity.strongEntity;


import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Table(name = "user")
public class UserEntity extends BaseEntity {
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
    private String autoLoginFlag;

    @Column(length = 10)
    private String status;

}
