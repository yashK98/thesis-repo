package com.ljmu.project.util;

import com.ljmu.project.model.Post;
import com.ljmu.project.model.User;

import java.util.HashMap;

public interface ServiceConstants {
    String CREATE_USER_ENDPOINT = "/createUser";
    String CREATE_POST_ENDPOINT = "/createPost";

    HashMap<String, Class<?>> CLASS_MAPPER = new HashMap<>(){{
        put("createUserResponse", User.class);
        put("createPostResponse", Post.class);
    }};
}
