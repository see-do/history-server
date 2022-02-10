package com.UMC.history.entity.weekEntity;

import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.BaseEntity;
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
}
