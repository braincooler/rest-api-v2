package de.assecor.personcolorrestapi.exceptions;

@SuppressWarnings("serial")
public class PersonNotFoundException extends RuntimeException{	
	public PersonNotFoundException(String message) {
		super(message);
	}
}
