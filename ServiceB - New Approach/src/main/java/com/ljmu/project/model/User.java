package com.ljmu.project.model;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID userId;
    private String name;
    private String username;
    private String email;
    private String mobile;
    private String profession;
    private String organization;
}
