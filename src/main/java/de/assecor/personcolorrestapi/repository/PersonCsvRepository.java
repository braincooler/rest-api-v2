package de.assecor.personcolorrestapi.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.assecor.personcolorrestapi.entity.ColorEntity;
import de.assecor.personcolorrestapi.entity.PersonEntity;

@Repository
public class PersonCsvRepository {

	private String path = "E:\\data.csv";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonCsvRepository.class);
	
	@Autowired
	private ColorRepository colorRepository;

	private List<PersonEntity> persons = new ArrayList<>();

	public List<PersonEntity> findAll() {
		initPersonsData();
		return persons;
	}

	public Optional<PersonEntity> findById(Long id) {
		initPersonsData();
		Optional<PersonEntity> result = persons.stream()
				.filter(p -> p.getId() == id)
				.findFirst();
		return result;
	}

	public List<PersonEntity> findByColorId(Long colorId) {
		initPersonsData();
		List<PersonEntity> result = persons.stream()
				.filter(p -> p.getColor().getId() == colorId)
				.collect(Collectors.toList());
		return result;
	}

	public PersonEntity save(PersonEntity person) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
			bw.newLine();
			bw.write(person.getLastname() + ", " + person.getFirstname() + ", " + person.getZipcode() + " "
					+ person.getCity() + ", " + (person.getColor().getId() + 1));
		} catch (Exception e) {
			LOGGER.error("Error on write csv", e);
		}
		initPersonsData();
		person.setId((long) persons.size());
		return person;
	}

	@PostConstruct
	public void initPersonsData() {
		persons.clear();
		String line = "";
		String splitBy = ",";
		Long index = 1L;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
			while ((line = br.readLine()) != null) {

				String[] data = line.split(splitBy);
				String lastname = data[0].trim();
				String firstname = data[1].trim();
				String zipcode = data[2].trim().substring(0, data[2].trim().indexOf(" "));
				String city = data[2].trim().substring(data[2].trim().indexOf(" ") + 1);
				Long colorId = Long.valueOf(data[3].replaceAll("[^\\d.]", ""));
				ColorEntity colorEntity = colorRepository.findById(colorId);
				PersonEntity person = new PersonEntity(index, lastname, firstname, zipcode, city, colorEntity);
				persons.add(person);
				index++;
			}

		} catch (IOException e) {
			LOGGER.error("Error on load csv", e);
		}
	}
}
