package com.ljmu.project.controller;

import com.ljmu.project.entity.Post;
import com.ljmu.project.entity.User;
import com.ljmu.project.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EntityController {

    @Autowired
    private EntityService entityService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        entityService.createUser(user);
    }

    @PostMapping("/createPost")
    public void createPost(@RequestBody Post post){
        entityService.createPost(post);
    }
}
