package com.ljmu.project.service;

import com.ljmu.project.entity.Post;
import com.ljmu.project.entity.User;
import com.ljmu.project.repo.PostRepo;
import com.ljmu.project.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EntityService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PostRepo postRepo;

    public void createUser(User user){
        log.info("Request Received for Creating User :: {}", user);
        userRepo.save(user);
    }

    public void createPost(Post post){
        log.info("Request Received for Creating POST :: {}", post);
        postRepo.save(post);
    }
}
