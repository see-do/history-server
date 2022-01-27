package com.UMC.history.entity.weekEntity;

import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Embeddable
@Table(name = "comment")
public class CommentEntity extends BaseEntity implements Serializable{
    @EmbeddedId
    private Comment comment;

    @Column(length = 500, nullable = false)
    private String contents;

    @Column(length = 10, columnDefinition = "char(10) default 'ACTIVE'")
    private String status;
}
