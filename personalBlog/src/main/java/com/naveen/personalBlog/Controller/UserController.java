package com.naveen.personalBlog.Controller;

import com.naveen.personalBlog.Models.User;
import com.naveen.personalBlog.Service.PostService;
import com.naveen.personalBlog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/signup")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public String getUserByuserId(@PathVariable Long user_id){
        return userService.finduserId(user_id).getUsername();
    }

    @GetMapping("/username/{username}")
    public Long getUserByUsername(@PathVariable String username){
        return userService.findByusername(username).getId();
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserAndreorderIds(id);
    }

}
