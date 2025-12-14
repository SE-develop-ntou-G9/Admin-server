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
    private BlacklistEntity toEntity(BlacklistDto dto) {
        BlacklistEntity entity = new BlacklistEntity();
        entity.setUserId(dto.getUserId());
        entity.setReason(dto.getReason());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }


    public BlacklistService(BlacklistRepository blacklistRepository) {
        this.blacklistRepository = blacklistRepository;
    }

    public BlacklistDto addUserToBlacklist(BlacklistDto dto) {
        // 1. 建立 Entity
        if (blacklistRepository.existsByUserId(dto.getUserId())) {
            throw new UserAlreadyBlacklistedException(
                    "userId already exists in blacklist"
            );
        }
        // 2. 交給 Repository 儲存
        BlacklistEntity savedEntity = blacklistRepository.save(toEntity(dto));

        return toDto(savedEntity);      // 回傳儲存後的資料
    }

    public ResponseEntity<List<BlacklistDto>> getBlackList(){
        List<BlacklistDto> dtos = blacklistRepository.findAll().stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<BlacklistEntity> delBlackList(String Id) {
        // 1. 查資料
        BlacklistEntity entity = blacklistRepository.findByUserId(Id)
                .orElseThrow(() -> new RuntimeException("User not in blacklist"));
        // 2. 刪除
        blacklistRepository.delete(entity);
        // 3. 回傳被刪除的資料
        return ResponseEntity.ok(entity);
    }

    public class UserAlreadyBlacklistedException extends RuntimeException {

        public UserAlreadyBlacklistedException(String message) {
            super(message);
        }
    }

}
