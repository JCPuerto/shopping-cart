package com.jcpuerto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jcpuerto.dao.PersonRepository;
import com.jcpuerto.dao.ProductRepository;
import com.jcpuerto.entities.Person;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	public PersonRepository personDb;

	@Autowired
	public ProductRepository productDb;

	@GetMapping()
	public String index(Model model) {

		logger.info("index(Model model)");

		model.addAttribute("person", new Person());

		return "home";
	}

	@PostMapping()
	public String index(@ModelAttribute Person person, Model model) {

		logger.info("index(@ModelAttribute Person person)");

		person = personDb.findByFirstName(person.getFirstName());

		if (person != null) {
			logger.info("person found!");
			model.addAttribute("products", productDb.findAll());
		}

		return person != null ? "products" : "home";
	}
}
