package com.example.admin.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
@Getter
@Setter
public class BlacklistDto {
    @NotBlank(message = "userId 不可為空")
    private String userId;
    private String reason;
    private LocalDateTime createdAt;
}
