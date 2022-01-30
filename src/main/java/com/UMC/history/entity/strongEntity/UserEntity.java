package com.UMC.history.entity.strongEntity;


import com.UMC.history.util.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private boolean lockScreen;

    @Column(columnDefinition = "boolean default false")
    private boolean autoLoginFlag;

    @Column(length = 10)
    private String status;

    @Builder
    public UserEntity(String nickName, String userid, String password, String profileImgUrl, boolean lockScreen, boolean autoLoginFlag, String status) {
        this.nickName = nickName;
        this.userid = userid;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
        this.lockScreen = lockScreen;
        this.autoLoginFlag = autoLoginFlag;
        this.status = status;
    }


}
