package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.dao.BaseDao;
import com.example.enkai.entity.EventUser;
import com.example.enkai.entity.EventUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUserService implements BaseService<EventUser> {
    @Autowired
    private BaseDao<EventUser> eventUserDao;

    @Override
    public List<EventUser> findAll() {
        return eventUserDao.findAll();
    }

    @Override
    public EventUser findById(Integer id) throws DataNotFoundException {
        return eventUserDao.findById(id);
    }

    @Override
    public void save(EventUser eventUser) {
        eventUserDao.save(eventUser);
    }

    @Override
    public void deleteById(Integer id) {
        eventUserDao.deleteById(id);
    }
}

