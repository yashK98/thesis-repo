package com.ljmu.project.repo;

import com.ljmu.project.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends CrudRepository<Post, String> {
}
