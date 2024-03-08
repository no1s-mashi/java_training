package com.example.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByEmail(String email);
}
