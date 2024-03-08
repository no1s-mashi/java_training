package com.example.order.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.order.common.DataNotFoundException;
import com.example.order.entity.User;
import com.example.order.repository.UserRepository;

@Repository
public class UserDao implements BaseDao<User> {
    @Autowired
    UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Integer id) throws DataNotFoundException {
        return this.repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(User user) {
        this.repository.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            User user = this.findById(id);
            this.repository.deleteById(user.getId());
        } catch (DataNotFoundException e) {
            System.out.println("no data");
        }
    }

    public User findByEmail(String email) throws DataNotFoundException {
        User user = this.repository.findByEmail(email);
        if (user == null) {
            throw new DataNotFoundException();
        }
        return user;
    }
}
