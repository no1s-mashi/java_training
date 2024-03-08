package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.dao.BaseDao;
import com.example.enkai.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements BaseService<Chat> {
    @Autowired
    private BaseDao<Chat> chatDao;

    @Override
    public List<Chat> findAll() {
        return chatDao.findAll();
    }

    @Override
    public Chat findById(Integer id) throws DataNotFoundException {
        return chatDao.findById(id);
    }

    @Override
    public void save(Chat chat) {
        chatDao.save(chat);
    }

    @Override
    public void deleteById(Integer id) {
        chatDao.deleteById(id);
    }
}

