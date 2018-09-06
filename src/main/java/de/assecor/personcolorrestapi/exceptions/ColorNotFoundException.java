package de.assecor.personcolorrestapi.exceptions;

@SuppressWarnings("serial")
public class ColorNotFoundException extends RuntimeException{

	public ColorNotFoundException(String message) {
		super(message);
	}
}
