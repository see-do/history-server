package com.UMC.history.entity.strongEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category")
public class CategoryEntity {
    @Id
    private Long categoryIdx;

    @Column(nullable = false, length = 50)
    private String categoryName;

    @Builder
    public CategoryEntity(String categoryName){
        this.categoryName = categoryName;
    }

}
