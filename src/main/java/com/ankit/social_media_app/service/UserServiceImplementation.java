package com.ankit.social_media_app.service;

import com.ankit.social_media_app.models.User;
import com.ankit.social_media_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());


        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            return user.get();// To get the user if it is present
        throw new Exception("User not Exist with User id"+userId);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userId1, Integer userId2) throws Exception {// User1 , User2 ko follow kar rha hai
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());
        userRepository.save(user1);
        userRepository.save(user2);

        return user1;
    }

    @Override
    public User updateUser(User user, Integer userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);
        if(user1.isEmpty()){
            throw new Exception("User not exist with this id "+userId);
        }

        User oldUser= user1.get();

        if(user.getFirstName()!=null)
            oldUser.setFirstName(user.getFirstName());

        if(user.getLastName()!=null)
            oldUser.setLastName(user.getLastName());
        if(user.getEmail()!=null)
            oldUser.setEmail(user.getEmail());

        return userRepository.save(oldUser);
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
