package com.example.order.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.order.common.DataNotFoundException;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;

@Repository
public class OrderDao implements BaseDao<Order> {
    @Autowired
    OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(Integer id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(Order order) {
        this.repository.save(order);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            Order order = this.findById(id);
            this.repository.deleteById(order.getId());
        } catch (DataNotFoundException e) {
            System.out.println("no data");
        }
    }
}
