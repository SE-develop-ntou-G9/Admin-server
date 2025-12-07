package com.example.admin.models;

import java.time.LocalDate;

public class ReviewRequest {
    private String ID;
    private String userID;
    private String status;
    private String payload; //JSON or summary
    private LocalDate createdAt;
}
