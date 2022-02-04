package com.UMC.history.entity.weekEntity;

import com.UMC.history.util.CategoryEnum;
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

    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false, length = 100)
    private String question;

    @Column(nullable = false)
    private boolean answer;

    @Column(nullable = false, length = 500)
    private String solution;

    @Builder
    public QuizEntity(CategoryEnum category, String question, boolean answer, String solution){
        this.category = category;
        this.question = question;
        this.answer = answer;
        this.solution = solution;
    }
}
