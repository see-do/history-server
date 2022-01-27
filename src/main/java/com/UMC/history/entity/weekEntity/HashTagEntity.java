package com.UMC.history.entity.weekEntity;

import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Embeddable
@Table(name = "hashtag")
public class HashTagEntity extends BaseEntity implements Serializable{
    @EmbeddedId
    private HashTag hashTag;

    @Column(nullable = false, length = 45)
    private String tag;

    @Column(length = 10, columnDefinition = "char(10) default 'ACTIVE'")
    private String status;
}
