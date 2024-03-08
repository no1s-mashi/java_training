package com.example.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}
