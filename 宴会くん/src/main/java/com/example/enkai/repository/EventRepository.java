package com.example.enkai.repository;

import com.example.enkai.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer>{

    List<Event> findByCategoryId(Integer categoryId);
    List<Event> findByUserEmail(String email);
}
