package com.naveen.personalBlog.Service;

import com.naveen.personalBlog.Models.Post;
import com.naveen.personalBlog.Models.User;
import com.naveen.personalBlog.Repository.PostRepository;
import com.naveen.personalBlog.Repository.UserRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post createPost(Long userId, Post post) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found !"));
        post.setUser(user);
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post Not Found !"));
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        return postRepository.save(post);
    }

    public String getUsernameByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post Not Found !"));
        return post.getUser().getUsername();
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deletePostAndreorderIds(Long id) {
        //delete the user
        postRepository.deleteById(id);

        //fetch all remaining users ordered by ID
        List<Post> posts = postRepository.findAllByOrderByIdAsc();

        //update IDs in database using native SQL
        long newId = 1;
        for (Post post : posts) {
            entityManager.createNativeQuery("UPDATE post SET id = ?1  where id = ?2")
                    .setParameter(1, newId++)
                    .setParameter(2, post.getId())
                    .executeUpdate();
        }

        //Reset auto-increment counter
        entityManager.createNativeQuery("ALTER TABLE post AUTO_INCREMENT = :nextId")
                .setParameter("nextId", newId)
                .executeUpdate();

    }
}
