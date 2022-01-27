package com.UMC.history.entity.weekEntity;

import com.UMC.history.entity.strongEntity.CategoryEntity;

import javax.persistence.*;

@Entity
@Table(name = "quiz")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizIdx;

    @ManyToOne
    @JoinColumn(name = "categoryIdx")
    private CategoryEntity category;

    @Column(nullable = false, length = 100)
    private String question;

    @Column(nullable = false)
    private Boolean answer;

    @Column(nullable = false, length = 500)
    private String solution;



}
