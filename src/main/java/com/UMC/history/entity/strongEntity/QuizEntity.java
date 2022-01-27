package com.UMC.history.entity.strongEntity;

import com.UMC.history.entity.strongEntity.CategoryEntity;

import javax.persistence.*;

@Entity
@Table(name = "quiz")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizIdx;

    @ManyToOne
    @JoinColumn(name = "categoryIdx", nullable = false) //null 값이면 안되는 total constraint 이기 때문
    private CategoryEntity category;

    @Column(nullable = false, length = 100)
    private String question;

    @Column(nullable = false)
    private Boolean answer;

    @Column(nullable = false, length = 500)
    private String solution;



}
