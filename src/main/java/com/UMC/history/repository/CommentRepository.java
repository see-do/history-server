package com.UMC.history.repository;

import com.UMC.history.entity.strongEntity.PostEntity;
import com.UMC.history.entity.weekEntity.CommentEntity;
import com.UMC.history.mappingInterface.CommentMappingInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentMappingInterface> findByPost(PostEntity postIdx);
}
