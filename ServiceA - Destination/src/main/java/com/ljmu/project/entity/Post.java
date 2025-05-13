package com.ljmu.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name="posts", schema="thesis")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private UUID postId;
    @Column(name = "post_name")
    private String postName;
    @Column(name = "content")
    private String content;
    @Column(name = "post_date")
    private String postDate;
    @Column(name = "user_id")
    private UUID userId;
}
