package com.lorian.lorianBank.exceptions.custom;

public class EmailNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailNotFoundException(String msg) {
		super(msg);
	}

	
}
