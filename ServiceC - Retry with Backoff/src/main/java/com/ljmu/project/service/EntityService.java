package com.ljmu.project.service;

import com.ljmu.project.gateway.EntityGateway;
import com.ljmu.project.model.Post;
import com.ljmu.project.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EntityService {

    @Autowired
    private EntityGateway entityGateway;

    public ResponseEntity<?> createUser(User user){
        log.info("Request Received for Creating User :: {}", user);
        return entityGateway.createUserResponse(user);
    }

    public ResponseEntity<?> createPost(Post post){
        log.info("Request Received for Creating POST :: {}", post);
        return entityGateway.createPostResponse(post);
    }
}
