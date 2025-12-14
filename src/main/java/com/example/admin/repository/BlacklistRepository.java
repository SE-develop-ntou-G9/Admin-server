package com.example.admin.repository;

import com.example.admin.entity.BlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<BlacklistEntity, Long> {
    Optional<BlacklistEntity> findByUserId(String userId);
    boolean existsByUserId(String userId);
    void deleteByUserId(String userId);
}
