package com.UMC.history.repository;

import com.UMC.history.entity.weekEntity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageReposotory extends JpaRepository<ImageEntity, Long> {
}
