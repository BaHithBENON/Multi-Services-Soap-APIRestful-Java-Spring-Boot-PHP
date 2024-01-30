package com.javaws.client;

import com.app.controllers.ClientAppController;

public class Launcher {
	static ClientAppController clientAppController;
	
	Launcher() {
		clientAppController = new ClientAppController();
	}
	
	public static void main(String[] args) {
		clientAppController = new ClientAppController();
		clientAppController.start();
	}
}
