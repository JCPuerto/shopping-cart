package com.jcpuerto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class ConfigRest extends RepositoryRestConfigurerAdapter {

	private static final String MY_BASE_URI_URI = "/rest";

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.setBasePath(MY_BASE_URI_URI);
	}
}
