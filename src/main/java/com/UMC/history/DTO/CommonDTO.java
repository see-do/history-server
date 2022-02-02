package com.UMC.history.DTO;

import com.UMC.history.util.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CommonDTO {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class Post{
        private String userId;
        private CategoryEnum category;
        private String title;
        private String contents;
        private List<String> hashTags;

        public Post(){}
    }
}
