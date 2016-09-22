package com.jcpuerto.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jcpuerto.entities.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
