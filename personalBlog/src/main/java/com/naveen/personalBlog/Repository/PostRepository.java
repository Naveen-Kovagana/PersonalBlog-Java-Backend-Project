package com.naveen.personalBlog.Repository;

import com.naveen.personalBlog.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
    List<Post> findAllByOrderByIdAsc();
}
