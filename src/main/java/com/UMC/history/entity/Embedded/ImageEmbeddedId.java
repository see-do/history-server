package com.UMC.history.entity.Embedded;


import com.UMC.history.entity.strongEntity.PostEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Embeddable
public class ImageEmbeddedId implements Serializable {
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageIdx;

    @ManyToOne
    @JoinColumn(name = "postIdx", nullable = false)
    private PostEntity post;
}
