package de.assecor.personcolorrestapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.assecor.personcolorrestapi.entity.ColorEntity;
import de.assecor.personcolorrestapi.entity.PersonEntity;
import de.assecor.personcolorrestapi.model.Color;
import de.assecor.personcolorrestapi.model.Person;
import de.assecor.personcolorrestapi.repository.ColorJpaRepository;
import de.assecor.personcolorrestapi.repository.ColorRepository;
import de.assecor.personcolorrestapi.repository.PersonCsvRepository;
import de.assecor.personcolorrestapi.repository.PersonJpaRepository;

@Service
public class PersonService {

	@Autowired
	private PersonCsvRepository personRepository;
	@Autowired
	private ColorRepository colorRepository;
	
//	@Autowired
//	private PersonJpaRepository personRepository;
//	@Autowired
//	private ColorJpaRepository colorRepository;
	

	public List<Person> findAll() {		
		return personRepository.findAll()
				.stream()
				.map(p -> mapToPerson(p))
				.collect(Collectors.toList());
	}

	public Optional<Person> findById(Long personId) {	
		Optional<PersonEntity> person = personRepository.findById(personId);
		if(person.isPresent())
			return Optional.of(mapToPerson(person.get()));
		return Optional.empty();
	}

	public List<Person> findByColorId(Long colorId) {
		return personRepository.findByColorId(colorId)
				.stream()
				.map(p -> mapToPerson(p))
				.collect(Collectors.toList());
	}

	public Person save(Person p) {
		ColorEntity colorEntity = colorRepository.getColorEntityByColor(p.getColor());
		PersonEntity personEntity = new PersonEntity(p.getId(), p.getLastname(), p.getFirstname(), p.getZipcode(), p.getCity(), colorEntity);
		PersonEntity savedPerson = personRepository.save(personEntity);
		return (mapToPerson(savedPerson));		
	}
	
	private Person mapToPerson(PersonEntity p) {
		Person person = new Person(p.getId(),
				p.getLastname(), 
				p.getFirstname(), 
				p.getZipcode(), 
				p.getCity(), 
				p.getColor().getColor());
		return person;
	}
}
