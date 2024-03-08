package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.common.DataNotFoundException;
import com.example.order.dao.BaseDao;
import com.example.order.entity.OrderDetail;

@Service
public class OrderDetailService implements BaseService<OrderDetail> {
    @Autowired
    private BaseDao<OrderDetail> dao;

    @Override
    public List<OrderDetail> findAll() {
        return dao.findAll();
    }

    @Override
    public OrderDetail findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(OrderDetail orderDetail) {
        dao.save(orderDetail);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}

