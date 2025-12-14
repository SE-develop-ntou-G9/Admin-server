package com.example.admin.services;

import com.example.admin.dto.BlacklistDto;
import com.example.admin.entity.BlacklistEntity;
import com.example.admin.repository.BlacklistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlacklistServiceTest {

    @Mock
    private BlacklistRepository blacklistRepository;

    @InjectMocks
    private BlacklistService blacklistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_should_return_saved_entity() {
        BlacklistEntity entity = new BlacklistEntity();
        BlacklistDto dto = new BlacklistDto();
        entity.setUserId("u123");
        entity.setReason("test");
        dto.setUserId(entity.getUserId());
        dto.setReason("test");

        when(blacklistRepository.save(entity)).thenReturn(entity);

        BlacklistDto result = blacklistService.addUserToBlacklist(dto);

        assertNotNull(result);
        assertEquals("u123", result.getUserId());
        verify(blacklistRepository, times(1)).save(entity);
    }
}
