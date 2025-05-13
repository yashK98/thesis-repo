package com.ljmu.project.service;

import com.ljmu.project.model.Post;
import com.ljmu.project.model.User;
import com.ljmu.project.gateway.EntityGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EntityService {

    @Autowired
    private EntityGateway entityGateway;

    public void createUser(User user){
        log.info("Request Received for Creating User :: {}", user);
        entityGateway.createUserResponse(user);
    }

    public void createPost(Post post){
        log.info("Request Received for Creating POST :: {}", post);
        entityGateway.createPostResponse(post);
    }
}
