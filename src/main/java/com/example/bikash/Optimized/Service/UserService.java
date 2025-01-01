package com.example.bikash.Optimized.Service;


import com.example.bikash.Optimized.Entities.User;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    User updateUser(Long id, User user);
    void deleteUser(Long id);

}
