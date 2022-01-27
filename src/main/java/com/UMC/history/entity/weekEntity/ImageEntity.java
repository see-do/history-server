package com.UMC.history.entity.weekEntity;

import com.UMC.history.util.BaseEntity;
import lombok.NoArgsConstructor;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Embeddable
@Table(name = "image")
public class ImageEntity extends BaseEntity implements Serializable{
    @EmbeddedId
    private Image image;

    @Column(nullable = false)
    private String imgUrl;

    @Column(length = 10, columnDefinition = "char(10) default 'ACTIVE'")
    private String status;
}
