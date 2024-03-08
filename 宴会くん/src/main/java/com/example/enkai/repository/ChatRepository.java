package com.example.enkai.repository;

import com.example.enkai.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer>{
}
