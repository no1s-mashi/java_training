package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.common.DataNotFoundException;
import com.example.order.dao.BaseDao;
import com.example.order.entity.Order;

@Service
public class OrderService implements BaseService<Order> {
    @Autowired
    private BaseDao<Order> dao;

    @Override
    public List<Order> findAll() {
        return dao.findAll();
    }

    @Override
    public Order findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(Order order) {
        dao.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}
