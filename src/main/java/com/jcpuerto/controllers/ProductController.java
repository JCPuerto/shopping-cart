package com.jcpuerto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcpuerto.dao.OrderRepository;
import com.jcpuerto.dao.ProductRepository;
import com.jcpuerto.dao.UserRepository;
import com.jcpuerto.entities.Product;
import com.jcpuerto.entities.User;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public UserRepository userDb;

	@Autowired
	public OrderRepository orderDb;

	@Autowired
	public ProductRepository productDb;

	@GetMapping()
	public String index(@RequestParam(required = false) Long userId, Model model) {

		logger.info("index(Long userId, Model model)");

		User user = null;

		if (userId != null)
			user = userDb.findOne(userId);

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("order", orderDb.findByUserId(user.getId()));
			model.addAttribute("products", productDb.findAll());

			return "products";
		}

		return "redirect:/";
	}

	@GetMapping("/{productId}")
	public String detail(@RequestParam(required = false) Long userId, @PathVariable("productId") long productId,
			Model model) {

		logger.info("detail(Long userId, long productId)");

		User user = null;

		if (userId != null)
			user = userDb.findOne(userId);

		Product product = productDb.findOne(productId);

		if (product == null)
			throw new ResourceNotFoundException();

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("order", orderDb.findByUserId(user.getId()));
			model.addAttribute("product", product);

			return "product-detail";
		}

		return "redirect:/";
	}
}
