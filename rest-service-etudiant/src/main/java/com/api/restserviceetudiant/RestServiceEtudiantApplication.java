package com.api.restserviceetudiant;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.controller.EtudiantController;
import com.api.controller.UserController;

@SpringBootApplication
public class RestServiceEtudiantApplication {

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(RestServiceEtudiantApplication.class);
		
		Collection<Class<?>> additionalPrimarySources = new ArrayList<Class<?>>();
		
		additionalPrimarySources.add(EtudiantController.class);
		additionalPrimarySources.add(UserController.class);
		
		application.addPrimarySources(additionalPrimarySources);
		
		application.run(args);
	}

}
