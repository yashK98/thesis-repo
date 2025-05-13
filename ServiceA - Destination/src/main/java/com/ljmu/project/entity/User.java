package com.ljmu.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="users", schema="thesis")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String name;
    private String username;
    private String email;
    private String mobile;
    private String profession;
    private String organization;
}
