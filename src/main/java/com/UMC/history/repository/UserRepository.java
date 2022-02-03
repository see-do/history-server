package com.UMC.history.repository;

import com.UMC.history.entity.strongEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUserId(String userId);

    boolean existsByUserId(String userId);
}
