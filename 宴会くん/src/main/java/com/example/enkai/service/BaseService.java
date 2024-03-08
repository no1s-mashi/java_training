package com.example.enkai.service;

import com.example.enkai.common.DataNotFoundException;

import java.util.List;

public interface BaseService<T> {
    public List<T> findAll();

    public T findById(Integer id) throws DataNotFoundException;

    public void save(T t);

    public void deleteById(Integer id);
}

