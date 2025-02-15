package com.naveen.personalBlog.Repository;

import com.naveen.personalBlog.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAllByOrderByIdAsc();

    User findByUserId(Long userId);

}
