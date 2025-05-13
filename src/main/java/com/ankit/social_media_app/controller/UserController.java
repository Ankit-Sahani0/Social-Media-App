package com.ankit.social_media_app.controller;

import com.ankit.social_media_app.models.User;
import com.ankit.social_media_app.repository.UserRepository;
import com.ankit.social_media_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    // Get All Users
    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    // Create a New User
    @PostMapping("/users")
    public User createUser(@RequestBody User user){

        return userService.registerUser(user);
    }


    // Get User By Id
    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId")Integer id) throws Exception {
        return userService.findUserById(id);
    }

    // Update a User By id
    @PutMapping("/user/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {
        User updatedUser = userService.updateUser(user, userId);
        return updatedUser;
    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {
        User user = userService.followUser(userId1,userId2);
        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);
        return users;

    }

/*

        Delete a User


    @DeleteMapping("user/{userId}")
    public String deleteUser(@PathVariable Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("User not exist with this id "+userId);
        }

        userRepository.delete(user.get());
        return "User Deleted Successfully";
    }

 */

}
