package com.UMC.history.entity.weekEntity;

import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name="comment")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentIdx;

    @JsonIgnore // interface를 사용하게 되면 원하는 글자 수만 나오게 하는 것을 할 수 없음
    @ManyToOne
    @JoinColumn(name = "postIdx")
    private PostEntity post;

    @ManyToOne
    @JoinColumn(name = "userIdx")
    private UserEntity user;

    @Column(nullable = false)
    private String contents;

    @Builder
    public CommentEntity(PostEntity post, UserEntity user, String contents){
        this.post = post;
        this.user = user;
        this.contents = contents;
    }
    public void changeContents(String contents) {
        this.contents = contents;
    }
    public void contentsLength(String contents) {
        if(contents.length() <30){
            this.contents = contents;
        } else this.contents = contents.substring(0, 30) + "...";
    }
}
