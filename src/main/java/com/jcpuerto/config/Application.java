package com.jcpuerto.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jcpuerto.dao.ProductRepository;
import com.jcpuerto.dao.UserRepository;
import com.jcpuerto.entities.Order;
import com.jcpuerto.entities.OrderItem;
import com.jcpuerto.entities.Product;
import com.jcpuerto.entities.User;

@SpringBootApplication
@EnableJpaRepositories("com.jcpuerto.*")
@EntityScan("com.jcpuerto.*")
public class Application {

	@Bean
	CommandLineRunner init(UserRepository userRepository, ProductRepository productRepository) {
		return (args) -> {

			// Products
			Map<String, Double> products = new HashMap<String, Double>();
			products.put("Garden item", 30.42);
			products.put("Machinery element", 22d);
			products.put("House tool", 40.05);
			products.put("Car feature", 16.50);
			products.put("Most wanted thing", 22.99);
			products.put("Unbeliavable piece", 85.0);

			products.entrySet().forEach(p -> {
				Product product = new Product();
				product.setName(p.getKey());
				product.setDescription(
						"Lorem ipsum dolor sit amet, platonem consectetuer voluptatibus vis eu, ex pri stet philosophia, aperiam perfecto mel eu. Te nec equidem maiorum, sed ea velit liber vocibus, impedit noluisse in sed.");
				product.setPrice(p.getValue());

				productRepository.save(product);
			});

			// Users
			Arrays.asList("Juan, Francis, Susan, Marce, Narcis, Ricaaardo".split(",")).forEach(u -> {
				User user = new User();
				user.setFirstName(u.trim());

				// Cart for Susana

				if (user.getFirstName().equals("Susan")) {
					Order order = new Order(user);
					user.addToOrders(order);

					Product product = productRepository.findOne(2l);
					OrderItem orderItem = new OrderItem(product);
					order.addToOrderItems(orderItem);

					product = productRepository.findOne(4l);
					orderItem = new OrderItem(product);
					order.addToOrderItems(orderItem);
				}

				userRepository.save(user);
			});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
