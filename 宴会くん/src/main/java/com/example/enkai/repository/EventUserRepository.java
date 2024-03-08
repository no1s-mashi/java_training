package com.example.enkai.repository;

import com.example.enkai.entity.Event;
import com.example.enkai.entity.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventUserRepository extends JpaRepository<EventUser, Integer>{
}
