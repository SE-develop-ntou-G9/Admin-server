package com.example.admin.controllers;

import com.example.admin.dto.BlacklistDto;
import com.example.admin.entity.BlacklistEntity;
import com.example.admin.services.BlacklistService;
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
    public String addToBlacklist(@RequestBody BlacklistDto dto) {
        blacklistService.addUserToBlacklist(dto);
        return "User added to blacklist.";
    }
    @GetMapping("/blacklist")
    public ResponseEntity<List<BlacklistDto>> getBlacklist() {
        return blacklistService.getBlackList();
    }
}
