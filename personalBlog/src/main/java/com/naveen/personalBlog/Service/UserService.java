package com.naveen.personalBlog.Service;

import com.naveen.personalBlog.Models.User;
import com.naveen.personalBlog.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        //calculate post count
        for(User user : users){
            user.setPostCount(user.getPosts() != null ? user.getPosts().size() : 0);
        }
        return users;
    }

    public User registerUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User finduserId(Long user_id) {
        return userRepository.findByUserId(user_id);
    }

    public User findByusername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void deleteUserAndreorderIds(Long id) {
        //delete the user
        userRepository.deleteById(id);

        //fetch all remaining users ordered by ID
        List<User> users = userRepository.findAllByOrderByIdAsc();

        //update IDs in database using native SQL
        long newId = 1;
        for (User user : users) {
            entityManager.createNativeQuery("UPDATE user SET id = ?1  where id = ?2")
                            .setParameter(1, newId++)
                                    .setParameter(2, user.getId())
                                            .executeUpdate();
        }

        //Reset auto-increment counter
        entityManager.createNativeQuery("ALTER TABLE user AUTO_INCREMENT = :nextId")
                .setParameter("nextId", newId)
                .executeUpdate();

    }
}
