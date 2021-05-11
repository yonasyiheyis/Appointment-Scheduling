package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private PersonRepository personRep;

	@Override
	public List<Person> getAll() {
		return personRep.findAll();

	}

	public Person findById(Integer id) {
		return personRep.findById(id).orElse(null);
	}

}
