package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;

@Repository
@Transactional
public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Integer> {

	@Query(value = "SELECT a FROM AppointmentRequest a WHERE a.person.id= ?1 and a.tmSession.id= ?2")
	AppointmentRequest findAppointmentRequests(Integer personId, Integer sessionId);
	
}