package com.example.bikash.Optimized.ServiceImpl;

import com.example.bikash.Optimized.Entities.User;
import com.example.bikash.Optimized.Repositories.UserRepository;
import com.example.bikash.Optimized.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Handle user not found appropriately
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email); // Assuming you added the custom query method
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null; // Handle user not found
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
