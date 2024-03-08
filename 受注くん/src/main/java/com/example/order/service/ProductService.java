package com.example.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.common.DataNotFoundException;
import com.example.order.dao.BaseDao;
import com.example.order.entity.Product;

@Service
public class ProductService implements BaseService<Product> {
    @Autowired
    private BaseDao<Product> dao;

    @Override
    public List<Product> findAll() {
        return dao.findAll();
    }

    @Override
    public Product findById(Integer id) throws DataNotFoundException {
        return dao.findById(id);
    }

    @Override
    public void save(Product product) {
        dao.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}
