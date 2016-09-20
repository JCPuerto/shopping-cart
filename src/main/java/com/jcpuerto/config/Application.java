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

import com.jcpuerto.dao.PersonRepository;
import com.jcpuerto.dao.ProductRepository;
import com.jcpuerto.entities.Person;
import com.jcpuerto.entities.Product;

@SpringBootApplication
@EnableJpaRepositories("com.jcpuerto.*")
@EntityScan("com.jcpuerto.*")
public class Application {

	@Bean
	CommandLineRunner init(PersonRepository personRepository, ProductRepository productRepository) {
		return (args) -> {
			// Users
			Arrays.asList("Juan, Francis, Susana".split(",")).forEach(p -> {
				Person person = new Person();
				person.setFirstName(p);

				personRepository.save(person);
			});

			// Products
			Map<String, Double> products = new HashMap<String, Double>();
			products.put("Product A", 55.12);
			products.put("Product B", 84d);
			products.put("Product C", 66.20);
			products.put("Product D", 48.50);
			products.put("Product E", 22.50);
			products.put("Product F", 64.99);

			products.entrySet().forEach(p -> {
				Product product = new Product();
				product.setName(p.getKey());
				product.setPrice(p.getValue());

				productRepository.save(product);
			});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}