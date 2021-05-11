package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Person;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
