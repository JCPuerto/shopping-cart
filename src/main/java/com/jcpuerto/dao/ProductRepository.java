package com.jcpuerto.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jcpuerto.entities.Product;

@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
