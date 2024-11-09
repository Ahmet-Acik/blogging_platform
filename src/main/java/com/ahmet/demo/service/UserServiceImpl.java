package com.ahmet.demo.service;

import com.ahmet.demo.dto.UserDTO;
import com.ahmet.demo.mapper.MainMapper;
import com.ahmet.demo.model.Post;
import com.ahmet.demo.exception.ResourceNotFoundException;
import com.ahmet.demo.model.User;
import com.ahmet.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MainMapper mainMapper;

    public User saveUser(User user) {
        // Add validation logic here
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Update fields of the existing user
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());

        // Update the collection properly
        Set<Post> existingPosts = existingUser.getPosts();
        Set<Post> newPosts = user.getPosts();

        if (newPosts != null) {
            // Remove posts that are no longer present
            existingPosts.removeIf(post -> !newPosts.contains(post));

            // Add or update posts
            for (Post newPost : newPosts) {
                if (!existingPosts.contains(newPost)) {
                    existingPosts.add(newPost);
                } else {
                    // Update the existing post if necessary
                    existingPosts.stream().filter(post -> post.equals(newPost)).forEach(post -> post.updateFrom(newPost));
                }
            }
        }

        logger.info("Updating user with id: {}", id);
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    public UserDTO convertToDto(User user) {
        return mainMapper.userToUserDTO(user);
    }

    public List<UserDTO> getAllUserDTOs() {
        return userRepository.findAll().stream().map(mainMapper::userToUserDTO).collect(Collectors.toList());
    }

    public UserDTO getUserDTOById(Long id) {
        User user = getUserById(id);
        return mainMapper.userToUserDTO(user);
    }
}