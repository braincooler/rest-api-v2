package de.assecor.personcolorrestapi;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.assecor.personcolorrestapi.controller.PersonController;
import de.assecor.personcolorrestapi.model.Person;
import de.assecor.personcolorrestapi.service.ColorService;
import de.assecor.personcolorrestapi.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonColorRestApiApplicationTests {

	@Autowired private MockMvc mockMvc;
	@Autowired private ObjectMapper objectMapper;

	@MockBean private PersonService personService;
	@MockBean private ColorService colorService;

	@Test
	public void getPersonsTest() throws Exception {
		Person person1 = new Person("Sabine", "Sorgenlos", "10115", "Berlin", "Lila");
		Person person2 = new Person("Max", "Musterman", "12681", "Berlin", "Blau");
		given(this.personService.findAll()).willReturn(Arrays.asList(person1, person2));
		this.mockMvc.perform(get("/persons").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].lastname").value("Sabine"))
				.andExpect(jsonPath("$[1].lastname").value("Max"))
				.andExpect(jsonPath("$[2].lastname").doesNotExist());
	}
	
	@Test
	public void getPersonByIdTest() throws Exception {
		Person person1 = new Person(1L, "Sabine", "Sorgenlos", "10115", "Berlin", "Lila");
		given(this.personService.findById(1L)).willReturn(person1);
		this.mockMvc.perform(get("/persons/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1L));
	}
	
	@Test
	public void savePersonTest() throws Exception {
		Person person1 = new Person(1L, "Sabine", "Sorgenlos", "10115", "Berlin", "Lila");
		this.mockMvc.perform(post("/persons").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(person1)))
				.andExpect(status().isCreated());
	}
} 
