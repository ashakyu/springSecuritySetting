package com.example.demo.config;


public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String userEmail) {
		super(userEmail + "NotFoundExcetpion");
	}

}
