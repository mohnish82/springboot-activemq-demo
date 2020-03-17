package com.desasha.activemq.demo;

public class Greeting {

	String message;

	public Greeting() {}
	
	public Greeting(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String toString() {
		return "Greeting [message=" + message + "]";
	}
	
}
