package de.assecor.personcolorrestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.assecor.personcolorrestapi.model.Person;
import de.assecor.personcolorrestapi.service.PersonService;

@Controller
@RequestMapping(value="persons")
public class PersonController {
	
	@Autowired private PersonService personService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> getPersons(){
		List<Person> persons = personService.findAll();
		return ResponseEntity.ok(persons);		
	}
	
	@GetMapping(value="/{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> getPersonById (@PathVariable Long personId){
		return ResponseEntity.ok(personService.findById(personId));
	}
	
	@GetMapping(value = "/color/{color}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Person>> getPersonsWithColor(@PathVariable Long color){
		List<Person> persons = personService.findByColorId(color);
		return ResponseEntity.ok(persons);		
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Person> savePerson(@RequestBody @Valid Person person){
		return ResponseEntity.status(201).body(personService.save(person));
	}
}
