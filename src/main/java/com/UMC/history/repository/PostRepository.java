package com.UMC.history.repository;

import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.util.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    //    List<PostEntity> findByCategory(CategoryEnum category);
    List<CommonDTO.UserProtected> findByUser(UserEntity userIdx);
    List<PostEntity> findByContentsContainsOrderByCreatedDateDesc(String keyword);
    List<PostEntity> findByTitleContainsOrderByCreatedDateDesc(String keyword);

    @Query(value="SELECT * FROM post order by rand() limit 1",nativeQuery = true)
    PostEntity findByOrderByRand();

    List<PostEntity> findByCategoryOrderByCreatedDateDesc(CategoryEnum category);

    List<PostEntity> findByOrderByCreatedDateDesc();

    List<PostEntity> findByCategoryOrderByTotalLikeDesc(CategoryEnum category);

    List<PostEntity> findByOrderByTotalLikeDesc();

//    List<CommonDTO.UserProtected> findByUserOrderByCreatedDateDesc(UserEntity userEntity);

    List<PostEntity> findByUserOrderByCreatedDateDesc(UserEntity userEntity);
}
