package de.assecor.personcolorrestapi.model;

public class Color {
	
	private Long id;
	private String color;
	
	public Color() {
		super();
	}
	
	public Color(Long id, String color) {
		super();
		this.id = id;
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
