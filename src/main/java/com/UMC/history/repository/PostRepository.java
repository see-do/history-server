package com.UMC.history.repository;

import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
//    List<PostEntity> findByCategory(CategoryEnum category);
    List<CommonDTO.UserProtected> findByUser(UserEntity userIdx);
    List<CommonDTO.UserProtected> findByContentsContains(String keyword);
    List<CommonDTO.UserProtected> findByTitleContains(String keyword);

    List<PostEntity> findByCategoryOrderByCreatedDateDesc(CategoryEnum category);

    List<PostEntity> findByCategoryOrderByTotalLikeDesc(CategoryEnum category);

    List<CommonDTO.UserProtected> findByUserOrderByCreatedDateDesc(UserEntity userEntity);
}
