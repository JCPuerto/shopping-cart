package com.jcpuerto.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.jcpuerto.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	List<User> findByLastName(@Param("name") String name);

	User findByFirstName(@Param("name") String name);
}
