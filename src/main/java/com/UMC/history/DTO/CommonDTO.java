package com.UMC.history.DTO;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.util.CategoryEnum;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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

    public interface UserProtected{
        Long getPostIdx();
        UserEntity getUser();
        CategoryEnum getCategory();
        String getTitle();
        String getContents();
        int getTotalClick();
        int getTotalLike();
        LocalDateTime getCreatedDate();
        LocalDateTime getLastModifedDate();
        List<String> getHashTags();
        List<MultipartFile> getImages();

        interface UserEntity{
            String getUserId();
            String getNickName();
            String getProfileImgUrl();
        }
    }

    public interface LikeUserProtected{
        Long getLikeIdx();
        UserProtected getPost();
        LocalDateTime getCreatedDate();
        LocalDateTime getLastModifedDate();
    }
}
