package com.example.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
}
