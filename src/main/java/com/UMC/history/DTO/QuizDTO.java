package com.UMC.history.DTO;

import com.UMC.history.util.CategoryEnum;
import lombok.*;

@NoArgsConstructor
public class QuizDTO {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Quiz{
        private CategoryEnum category;
        private String question;
        private Boolean answer;
        private String solution;

        public Quiz(){}
    }
}
