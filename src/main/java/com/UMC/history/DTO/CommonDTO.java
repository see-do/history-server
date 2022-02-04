package com.UMC.history.DTO;

import com.UMC.history.util.CategoryEnum;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
public class CommonDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class Post{
        private String userId;
        private CategoryEnum category;
        private String title;
        private String contents;
        private List<String> hashTags;
        private List<MultipartFile> imageList;

        public Post(){}
    }
}
