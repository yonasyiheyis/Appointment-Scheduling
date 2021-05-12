package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Appointment;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	@Query(value = "SELECT a FROM Appointment a WHERE a.person.id= ?1 and a.tmSession.id= ?2")
	Appointment findAppointments(Integer personId, Integer sessionId);

	@Query(value = "SELECT a FROM Appointment a WHERE a.person.id= ?1")
	List<Appointment> findAppointments(Integer personId);

}