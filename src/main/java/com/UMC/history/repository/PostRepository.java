package com.UMC.history.repository;

import com.UMC.history.DTO.CommonDTO;
import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.entity.weekEntity.LikeEntity;
import com.UMC.history.util.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<CommonDTO.UserProtected> findByCategory(CategoryEnum category);
    List<CommonDTO.UserProtected> findByUser(UserEntity userIdx);
//    List<CommonDTO.UserProtected> findAllByLikeList(List<LikeEntity> lvR);
}
