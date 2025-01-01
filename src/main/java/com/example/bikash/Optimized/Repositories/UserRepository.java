
package com.example.bikash.Optimized.Repositories;

import com.example.bikash.Optimized.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}