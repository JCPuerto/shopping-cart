package com.jcpuerto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcpuerto.dao.PersonRepository;
import com.jcpuerto.entities.Person;

@RestController
@RequestMapping("people/")
public class PersonRestController {
	@Autowired
	public PersonRepository personDb;

	@RequestMapping("/")
	public Iterable<Person> people(@RequestParam(value = "name", defaultValue = "World") String name) {

		// Continue here...

		return personDb.findAll();
	}
}
