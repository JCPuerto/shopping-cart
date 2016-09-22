package com.jcpuerto.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jcpuerto.dao.OrderItemRepository;
import com.jcpuerto.dao.OrderRepository;
import com.jcpuerto.dao.ProductRepository;
import com.jcpuerto.dao.UserRepository;
import com.jcpuerto.entities.Order;
import com.jcpuerto.entities.OrderItem;
import com.jcpuerto.entities.Product;
import com.jcpuerto.entities.User;

@RestController
@RequestMapping("/rest/users/{userId}/cart")
public class CartRestController {

	private final Logger logger = LoggerFactory.getLogger(CartRestController.class);

	@Autowired
	public UserRepository userDb;

	@Autowired
	public OrderRepository orderDb;

	@Autowired
	public OrderItemRepository orderItemDb;

	@Autowired
	public ProductRepository productDb;

	@GetMapping()
	public Order getCart(@PathVariable("userId") long userId) {

		logger.info("getCart(long userId)");

		Order order = orderDb.findByUserId(userId);

		return order;
	}

	@PostMapping()
	public Order addToCart(@PathVariable("userId") long userId, @RequestBody Product product) {

		logger.info("addToCart()");

		Order order = orderDb.findByUserId(userId);

		product = productDb.findOne(product.getId());

		if (product == null)
			return null;

		if (order == null) {
			User user = userDb.findOne(userId);
			order = new Order(user);
		}

		if (order.getUser() == null)
			return null;

		boolean found = false;
		for (OrderItem orderItem : order.getOrderItems()) {
			if (orderItem.getProduct().getId() == product.getId()) {
				System.out.println("Adding an existing item");
				orderItem.addQty(product, 1);
				order.updateOrder(product.getPrice(), 1);
				found = true;
			}
		}

		if (!found)
			order.addToOrderItems(new OrderItem(product));

		orderDb.save(order);

		return order;
	}

	@DeleteMapping("/{orderItemId}")
	public Order removeFromCart(@PathVariable("userId") long userId, @PathVariable("orderItemId") long orderItemId,
			@RequestBody Product product) {

		logger.info("removeFromCart()");

		OrderItem orderItem = orderItemDb.findOne(orderItemId);

		if (orderItem.getOrder().getUser().getId() != userId) {
			logger.info("orderItem doesn't belong to logged user");
			return null;
		}

		Order order = orderItem.getOrder();

		if (orderItem.getProduct().getId() != product.getId()) {
			logger.info("orderItem doesn't have that product");
			return null;
		} else
			product = orderItem.getProduct(); // to update the price from the db

		orderItem.addQty(product, -1);

		String d = "";

		if (orderItem.getQty() == 0)
			order.removeFromOrderItems(orderItem);
		else
			order.updateOrder(-product.getPrice(), -1);

		orderItemDb.save(orderItem);

		logger.info("order: " + order.getId());

		return order;
	}
}
