package com.mars.robots.exceptions;

/*
 * A custom Exception class to capture events for bad Instructions
 * */
public class InvalidInstructionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidInstructionException(char instruction) {
		super("Invalid instruction character: " + instruction);
	}
	public InvalidInstructionException(String instructionLine) {
		super("Instructions size not valid: " + instructionLine.length());
	}	
}