package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Person;

public interface IPersonService {

	public Person getPersonById(Integer personId);

	public List<Person> getAllPerson();

	public void createPerson(Person person);

	public void deletePersonByID(Integer person);

	public Person updatePersonByID(Integer person_id, Person person);
	
	

}
