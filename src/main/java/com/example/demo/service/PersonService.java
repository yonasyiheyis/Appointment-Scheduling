package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public Person getPersonById(Integer personId) {
		return personRepository.findById(personId).orElse(null);
	}

	@Override
	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	@Override
	public void createPerson(Person person) {
		Person per = new Person();
		per.setEmailAddress(person.getEmailAddress());
		per.setFirstName(person.getFirstName());
		per.setLastName(person.getLastName());
		per.setPassword(person.getPassword());
		per.setUsername(person.getUsername());
		per.setRoles(person.getRoles());
		personRepository.save(per);
	}

	@Override
	public void deletePersonByID(Integer person) {
		Person per = personRepository.findById(person).get();
		if (per != null)
			personRepository.delete(per);
	}

	@Override
	public Person updatePersonByID(Integer person_id, Person person) {
		Person per = personRepository.findById(person_id).get();
		if (per != null) {
			per.setEmailAddress(person.getEmailAddress());
			per.setFirstName(person.getFirstName());
			per.setLastName(person.getLastName());
			per.setPassword(person.getPassword());
			per.setUsername(person.getUsername());
			per.setRoles(person.getRoles());
			personRepository.save(per);

		}
		return per;
	}

}
