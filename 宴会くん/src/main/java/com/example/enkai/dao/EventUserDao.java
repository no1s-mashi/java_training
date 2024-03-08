package com.example.enkai.dao;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.entity.Event;
import com.example.enkai.entity.EventUser;
import com.example.enkai.repository.EventRepository;
import com.example.enkai.repository.EventUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventUserDao implements BaseDao<EventUser> {
    @Autowired
    EventUserRepository repository;

    @Override
    public List<EventUser> findAll() {
        return repository.findAll();
    }

    @Override
    public EventUser findById(Integer id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(EventUser eventUser) {
        this.repository.save(eventUser);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            EventUser eventUser = this.findById(id);
            this.repository.deleteById(eventUser.getId());
        } catch (DataNotFoundException e) {
            System.out.println("no data");
        }
    }
}
