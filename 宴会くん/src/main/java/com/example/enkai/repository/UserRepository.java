package com.example.enkai.repository;

import com.example.enkai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByEmail(String email);
}
