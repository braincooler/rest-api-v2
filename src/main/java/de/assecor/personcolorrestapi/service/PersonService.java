package de.assecor.personcolorrestapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import de.assecor.personcolorrestapi.entity.ColorEntity;
import de.assecor.personcolorrestapi.entity.PersonEntity;
import de.assecor.personcolorrestapi.exceptions.ColorNotFoundException;
import de.assecor.personcolorrestapi.exceptions.PersonNotFoundException;
import de.assecor.personcolorrestapi.model.Person;
import de.assecor.personcolorrestapi.repository.interfaces.ColorRepository;
import de.assecor.personcolorrestapi.repository.interfaces.PersonRepository;

@Service
public class PersonService {

	@Autowired @Qualifier("PersonCsvRepository")
	private PersonRepository personRepository;
	@Autowired @Qualifier("ColorCsvRepository")
	private ColorRepository colorRepository;

	public List<Person> findAll() {		
		return personRepository.findAll()
				.stream()
				.map(p -> mapToPerson(p))
				.collect(Collectors.toList());
	}

	public Person findById(Long personId) {	
		PersonEntity person = personRepository.findById(personId)
				.orElseThrow(() -> new PersonNotFoundException("Person not found"));
		return mapToPerson(person);
	}

	public List<Person> findByColorId(Long colorId) {
		return personRepository.findByColorId(colorId)
				.stream()
				.map(p -> mapToPerson(p))
				.collect(Collectors.toList());
	}

	public Person save(Person person) {		
		ColorEntity colorEntity = colorRepository.getColorEntityByColor(person.getColor())
				.orElseThrow(() -> new ColorNotFoundException("Color " + person.getColor() + " not found"));
		PersonEntity personEntity = new PersonEntity(person.getId(), 
				person.getLastname(), 
				person.getFirstname(), 
				person.getZipcode(), 
				person.getCity(), 
				colorEntity);
		PersonEntity savedPerson = personRepository.save(personEntity);
		return (mapToPerson(savedPerson));		
	}
	
	private Person mapToPerson(PersonEntity personEntity) {
		Person person = new Person(personEntity.getId(),
				personEntity.getLastname(), 
				personEntity.getFirstname(), 
				personEntity.getZipcode(), 
				personEntity.getCity(), 
				personEntity.getColor().getColor());
		return person;
	}
}
