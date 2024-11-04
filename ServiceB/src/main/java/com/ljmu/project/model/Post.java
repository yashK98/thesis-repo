package com.ljmu.project.model;

import lombok.Data;
import java.util.UUID;

@Data
public class Post {
    private UUID postId;
    private String postName;
    private String content;
    private String postDate;
    private UUID userId;
}
