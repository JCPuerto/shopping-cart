package com.jcpuerto.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jcpuerto.dao.PersonRepository;
import com.jcpuerto.entities.Person;

@SpringBootApplication
@EnableJpaRepositories("com.jcpuerto.*")
@EntityScan("com.jcpuerto.*")
public class Application {

	@Bean
	CommandLineRunner init(PersonRepository personRepository) {
		return (evt) -> Arrays.asList("John, Peter, Juan".split(",")).forEach(a -> {
			Person person = new Person();
			person.setFirstName(a);

			personRepository.save(person);
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}