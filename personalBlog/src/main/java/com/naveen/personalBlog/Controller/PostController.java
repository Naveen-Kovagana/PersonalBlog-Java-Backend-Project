package com.naveen.personalBlog.Controller;

import com.naveen.personalBlog.Models.Post;
import com.naveen.personalBlog.Repository.UserRepository;
import com.naveen.personalBlog.Service.PostService;
import org.hibernate.dialect.unique.CreateTableUniqueDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public Post createPost(@PathVariable Long userId, @RequestBody Post post){
        return postService.createPost(userId, post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost ){
        return postService.updatePost(id, updatedPost);
    }

    @GetMapping("/{postId}/username")
    public ResponseEntity<String> getUsernameByPostId(@PathVariable Long postId){
        try {
            String username = postService.getUsernameByPostId(postId);
            return ResponseEntity.ok(username);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(404).body("Post Not Found !");
        }
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePostAndreorderIds(id);
    }

}
