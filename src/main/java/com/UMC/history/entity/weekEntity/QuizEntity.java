package com.UMC.history.entity.weekEntity;

import com.UMC.history.entity.strongEntity.CategoryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private boolean answer;

    @Column(nullable = false, length = 500)
    private String solution;

    @Builder
    public QuizEntity(CategoryEntity category, String question, boolean answer, String solution){
        this.category = category;
        this.question = question;
        this.answer = answer;
        this.solution = solution;
    }



}
