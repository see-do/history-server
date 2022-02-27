package com.UMC.history.repository;


import com.UMC.history.entity.weekEntity.QuizEntity;
import com.UMC.history.util.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Long> {
    @Query(value="SELECT * FROM quiz order by rand() Limit 5",nativeQuery = true)
    List<QuizEntity> findByOrderByRand();

    @Query(value="SELECT * FROM quiz WHERE category=:categoryName order by rand() Limit 5",nativeQuery = true)
    List<QuizEntity> findByCategoryOrderByRand(@Param("categoryName")String category);

    Boolean existsByQuizIdx(Long quizIdx);
}
