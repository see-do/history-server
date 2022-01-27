package com.UMC.history.entity.weekEntity;

import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Embeddable
@Table(name = "liked")
public class LikedEntity extends BaseEntity implements Serializable{
    @EmbeddedId
    private Liked liked;

    @Column(length = 1, columnDefinition = "char(1) default 'Y'")
    private String status;
}