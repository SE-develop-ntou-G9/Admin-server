package com.example.admin.controllers;

import com.example.admin.dto.BlacklistDto;
import com.example.admin.entity.BlacklistEntity;
import com.example.admin.services.BlacklistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class BlackListController {
    private final BlacklistService blacklistService;

    public BlackListController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @PostMapping("/blacklist")
    public ResponseEntity<BlacklistDto> addToBlacklist(@Valid @RequestBody BlacklistDto dto) {
        BlacklistDto ResDto = blacklistService.addUserToBlacklist(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResDto);
    }
    @GetMapping("/blacklist")
    public ResponseEntity<List<BlacklistDto>> getBlacklist() {
        return blacklistService.getBlackList();
    }
    @DeleteMapping("/blacklist/{id}")
    public String deleteBlacklist(@PathVariable String id) {
        blacklistService.delBlackList(id);
        return "delete blacklist successfully!.";
    }
}
