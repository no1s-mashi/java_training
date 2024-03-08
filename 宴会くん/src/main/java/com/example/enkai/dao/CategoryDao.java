package com.example.enkai.dao;

import com.example.enkai.common.DataNotFoundException;
import com.example.enkai.entity.Category;
import com.example.enkai.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDao implements BaseDao<Category> {
    @Autowired
    CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(Integer id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(Category category) {
        this.repository.save(category);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            Category category = this.findById(id);
            this.repository.deleteById(category.getId());
        } catch (DataNotFoundException e) {
            System.out.println("no data");
        }
    }
}
