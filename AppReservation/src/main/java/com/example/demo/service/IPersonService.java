package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Person;

public interface IPersonService {

	public List<Person> getAll();
	
	public Person findById(Integer id);
}
