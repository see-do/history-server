package com.UMC.history.entity.weekEntity;

import com.UMC.history.entity.strongEntity.PostEntity;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import javax.persistence.*;

@NoArgsConstructor
@Embeddable
public class Image implements Serializable {

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgIdx;

    @ManyToOne
    @JoinColumn(name = "postIdx", nullable = false)
    private PostEntity post;
}
