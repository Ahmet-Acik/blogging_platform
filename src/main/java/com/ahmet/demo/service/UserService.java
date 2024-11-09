package com.ahmet.demo.service;

import com.ahmet.demo.dto.UserDTO;
import com.ahmet.demo.model.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    UserDTO convertToDto(User user);
    List<UserDTO> getAllUserDTOs();
    UserDTO getUserDTOById(Long id);
}