package com.jcpuerto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jcpuerto.dao.OrderRepository;
import com.jcpuerto.dao.UserRepository;
import com.jcpuerto.entities.User;

@Controller
@RequestMapping("cart")
public class CartController {
	private final Logger logger = LoggerFactory.getLogger(CartController.class);

	@Autowired
	public UserRepository userDb;

	@Autowired
	public OrderRepository orderDb;

	@GetMapping()
	public String index(@RequestParam(required = false) Long userId, Model model) {

		logger.info("index(Long userId, Model model)");

		User user = null;

		if (userId != null)
			user = userDb.findOne(userId);

		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("order", orderDb.findByUserId(user.getId()));

			return "cart";
		}

		return "redirect:/";
	}
}
