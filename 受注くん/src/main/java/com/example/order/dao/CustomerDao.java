package com.example.order.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.order.common.DataNotFoundException;
import com.example.order.entity.Customer;
import com.example.order.repository.CustomerRepository;

@Repository
public class CustomerDao implements BaseDao<Customer> {
    @Autowired
    CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(Integer id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public void save(Customer customer) {
        this.repository.save(customer);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            Customer customer = this.findById(id);
            this.repository.deleteById(customer.getId());
        } catch (DataNotFoundException e) {
            System.out.println("no data");
        }
    }
}
