package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.dao.BaseDao;
import com.example.enkai.dao.EventDao;
import com.example.enkai.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements BaseService<Event> {
    @Autowired
    private EventDao eventDao;

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public Event findById(Integer id) throws DataNotFoundException {
        return eventDao.findById(id);
    }

    public List<Event> findByCategoryId(Integer categoryId) throws DataNotFoundException {
        return eventDao.findByCategoryId(categoryId);
    }

    public List<Event> findByUserEmail(String email) {
        return eventDao.findByUserEmail(email);
    }

    @Override
    public void save(Event event) {
        eventDao.save(event);
    }

    @Override
    public void deleteById(Integer id) {
        eventDao.deleteById(id);
    }
}

