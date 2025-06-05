package com.lorian.lorianBank.exceptions.custom;

public class NumeroNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NumeroNotFoundException(String msg) {
		super(msg);
	}
	
}
