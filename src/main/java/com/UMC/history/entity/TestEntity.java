package com.UMC.history.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "test")
public class TestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String dummyData;

    @Builder
    public TestEntity(String dummyData) {
        this.dummyData = dummyData;
    }
}
