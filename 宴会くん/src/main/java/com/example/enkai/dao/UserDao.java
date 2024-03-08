package com.example.enkai.dao;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.entity.User;
import com.example.enkai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
