package com.ljmu.project.controller;

import com.ljmu.project.model.Post;
import com.ljmu.project.model.User;
import com.ljmu.project.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EntityController {

    @Autowired
    private EntityService entityService;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return entityService.createUser(user);
    }

    @PostMapping("/createPost")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        return entityService.createPost(post);
    }
}
