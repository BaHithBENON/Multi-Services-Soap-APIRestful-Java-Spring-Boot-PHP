package com.javaws.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class WSHello {
	
	@WebMethod
	public String sayHello() {
		return "Hello world ! Welcome to my first web service.";
	}
}
