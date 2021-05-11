package com.example.demo.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.AppointmentRequestRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TMSessionRepository;
import com.example.demo.service.IAppointmentService;
import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;
import com.example.demo.domain.Person;
import com.example.demo.domain.TMSession;


@Service
public class AppointmentService implements IAppointmentService{
	
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Autowired
	private TMSessionRepository sessionRepo;
	
	@Autowired
	private AppointmentRequestRepository appRequestRepo;
	
	
//	public List<Appointment> getAppointments(int personId){
//		return personRepo.findById(personId).get().getAppointments();
//	}
//	
//	public AppointmentRequest getAppointment(int appointmentId) {
//		return appRequestRepo.findById(appointmentId).orElse(null);
//	}
	
	public AppointmentRequest editAppointment(int personId, int sessionId) {
		
		if (appointmentRepo.findAppointments(personId, sessionId) == null) return null;
		
		AppointmentRequest existingRequest = appRequestRepo.findAppointmentRequests(personId, sessionId);
		LocalDate aptDate = existingRequest.getTmSession().getSessionDate();
		LocalTime aptTime = existingRequest.getTmSession().getStartTime();
		LocalDateTime appointmentTime = LocalDateTime.of(aptDate, aptTime);
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime validTime = appointmentTime.minusHours(48);
		
		if (existingRequest != null && validTime.compareTo(currentTime) > 1) {
			Person client = personRepo.findById(personId).get();
			TMSession session = sessionRepo.findById(sessionId).get();
			existingRequest.setPerson(client);
			existingRequest.setTmSession(session);
		}
		AppointmentRequest persisted = appRequestRepo.save(existingRequest);
		
		return persisted;
	}

}
