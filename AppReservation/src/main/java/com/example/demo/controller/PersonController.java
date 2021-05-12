package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Person;
import com.example.demo.domain.TMSession;
import com.example.demo.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	// get all person

	@GetMapping("/GET/person")
	public List<Person> getAllPerson() {
		return personService.getAllPerson();
	}

	// get person by id

	@GetMapping("/GET/person/{person_id}")
	public Person getPersonById(@PathVariable("person_id") Integer person_id) {
		return personService.getPersonById(person_id);
	}

	// create person

	@PostMapping("/POST/person")
	public void postPerson(@RequestBody Person person) {
		personService.createPerson(person);
	}

	// delete person

	@DeleteMapping("DELETE/person/{person_id}")
	public void deletePersonByID(@PathVariable("person_id") Integer person_id) {
		personService.deletePersonByID(person_id);
	}

	// update person

	@PutMapping("PUT/person/{person_id}")
	public Person updatePersonByID(@PathVariable("person_id") Integer person_id, @RequestBody Person person) {
		return personService.updatePersonByID(person_id, person);
	}

}
