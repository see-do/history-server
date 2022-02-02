package com.UMC.history.repository;

import com.UMC.history.entity.weekEntity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long> {
}
