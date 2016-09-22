package com.jcpuerto.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.jcpuerto.entities.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

	Order findByUserId(@Param("userId") long userId);

	Order findByUserFirstName(@Param("name") String name);
}
