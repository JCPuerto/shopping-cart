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

import com.jcpuerto.dao.OrderRepository;
import com.jcpuerto.dao.ProductRepository;
import com.jcpuerto.dao.UserRepository;
import com.jcpuerto.entities.Product;
import com.jcpuerto.entities.User;

@Controller
@RequestMapping("/")
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	public UserRepository userDb;

	@Autowired
	public ProductRepository productDb;

	@Autowired
	public OrderRepository orderDb;

	@GetMapping()
	public String index(Model model) {

		logger.info("index(Model model)");

		model.addAttribute("user", new User());

		return "home";
	}

	@PostMapping()
	public String index(@ModelAttribute User user, Model model) {

		logger.info("index(User user, Model model)");

		user = userDb.findByFirstName(user.getFirstName());

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("order", orderDb.findByUserId(user.getId()));
			model.addAttribute("product", new Product());
			model.addAttribute("products", productDb.findAll());
		} else {
			model.addAttribute("error", true);
			model.addAttribute("user", new User());
		}

		return user != null ? "products" : "home";
	}
}
