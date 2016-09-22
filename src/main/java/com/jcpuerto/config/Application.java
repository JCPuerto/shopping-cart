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
			products.put("Product 1", 10.0);
			products.put("Product 2", 20d);
			products.put("Product 3", 30.0);
			products.put("Product 4", 40.00);
			products.put("Product 5", 50.00);
			products.put("Product 6", 60.0);

			products.entrySet().forEach(p -> {
				Product product = new Product();
				product.setName(p.getKey());
				product.setPrice(p.getValue());

				productRepository.save(product);
			});

			// Users
			Arrays.asList("Juan, Francis, Susana".split(",")).forEach(u -> {
				User user = new User();
				user.setFirstName(u.trim());

				// Cart for Susana

				if (user.getFirstName().equals("Susana")) {
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