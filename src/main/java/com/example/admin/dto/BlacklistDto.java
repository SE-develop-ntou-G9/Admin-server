package com.example.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class BlacklistDto {
    private String userId;
    private String reason;
    private LocalDateTime createdAt;
}
