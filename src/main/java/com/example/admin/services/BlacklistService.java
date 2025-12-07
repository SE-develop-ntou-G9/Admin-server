package com.example.admin.services;

import com.example.admin.entity.BlacklistEntity;
import com.example.admin.dto.BlacklistDto;
import com.example.admin.repository.BlacklistRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlacklistService {
    private final BlacklistRepository blacklistRepository;

    private BlacklistDto toDto(BlacklistEntity entity) {
        BlacklistDto dto = new BlacklistDto();
        dto.setUserId(entity.getUserId());
        dto.setReason(entity.getReason());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }


    public BlacklistService(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    public ResponseEntity<BlacklistEntity> addUserToBlacklist(BlacklistDto dto) {
        // 1. 建立 Entity
        BlacklistEntity entity = new BlacklistEntity();
        entity.setUserId(dto.getUserId());
        entity.setReason(dto.getReason());
        entity.setCreatedAt(LocalDateTime.now());
        // 2. 交給 Repository 儲存
        BlacklistEntity savedEntity = blacklistRepository.save(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)  // 201 表示已建立
                .body(savedEntity);          // 回傳儲存後的資料
    }

    public ResponseEntity<List<BlacklistDto>> getBlackList(){
        List<BlacklistDto> dtos = blacklistRepository.findAll().stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
