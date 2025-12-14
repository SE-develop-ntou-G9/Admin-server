package com.example.admin.controllers;

import com.example.admin.entity.BlacklistEntity;
import com.example.admin.services.BlacklistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlacklistController.class)
class BlacklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlacklistService blacklistService;

    @Test
    void create_should_return_200() throws Exception {
        BlacklistEntity entity = new BlacklistEntity();
        entity.setUserId("u123");

        when(blacklistService.create(any())).thenReturn(entity);

        mockMvc.perform(post("/blacklist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "userId": "u123",
                      "reason": "test"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("u123"));
    }
}
