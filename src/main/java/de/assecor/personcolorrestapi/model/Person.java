package de.assecor.personcolorrestapi.model;

public class Person {
	
	private Long id;
	private String lastname;
	private String firstname;
	private String zipcode;
	private String city;
	private String color;
	
	public Person() {
		super();
	}
	
	public Person(Long id, String lastname, String firstname, String zipcode, String city, String color) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.zipcode = zipcode;
		this.city = city;
		this.color = color;
	}
	
	public Person(String lastname, String firstname, String zipcode, String city, String color) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.zipcode = zipcode;
		this.city = city;
		this.color = color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", zipcode=" + zipcode
				+ ", city=" + city + ", color=" + color + "]";
	}
}
