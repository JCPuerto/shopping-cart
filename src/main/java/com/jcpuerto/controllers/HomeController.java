package com.jcpuerto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jcpuerto.dao.PersonRepository;
import com.jcpuerto.entities.Person;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	public PersonRepository personDb;

	@GetMapping()
	public String index(Model model) {

		model.addAttribute("person", new Person());

		return "index";
	}

	@PostMapping()
	public String index(@ModelAttribute Person model) {

		Person person = personDb.findByFirstName(model.getFirstName());

		return person != null ? "products" : "index";
	}
}
