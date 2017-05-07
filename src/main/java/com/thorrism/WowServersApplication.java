package com.thorrism;

import com.thorrism.config.JerseyConfig;
import com.thorrism.entity.Realm;
import com.thorrism.entity.Server;
import com.thorrism.repository.RealmRepository;
import com.thorrism.repository.ServerRepository;
import com.thorrism.util.InitUtils;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class WowServersApplication {

	@Autowired
	RealmRepository realmRepository;

	public static void main(String[] args) {
		SpringApplication.run(WowServersApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return registration;
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return (args) -> {
			Realm[] defaultServers = InitUtils.getDefaultRealms();
			if (defaultServers != null) {
				realmRepository.save(Arrays.asList(defaultServers));
			}
		};
	}
}
