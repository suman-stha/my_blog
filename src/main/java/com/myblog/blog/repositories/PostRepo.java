package com.myblog.blog.repositories;

import com.myblog.blog.entities.Category;
import com.myblog.blog.entities.Post;
import com.myblog.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
