package com.anjana.hotel_management_system.service;

import com.anjana.hotel_management_system.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
}
