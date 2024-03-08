package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.dao.BaseDao;
import com.example.enkai.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements BaseService<Category> {
    @Autowired
    private BaseDao<Category> categoryDao;

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Integer id) throws DataNotFoundException {
        return categoryDao.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryDao.deleteById(id);
    }
}

