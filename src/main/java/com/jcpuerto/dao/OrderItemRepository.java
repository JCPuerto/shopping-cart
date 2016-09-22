package com.jcpuerto.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jcpuerto.entities.OrderItem;

public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, Long> {

}
