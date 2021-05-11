package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;
import com.example.demo.service.AppointmentService;

@RestController
public class AppointmentController {
	
	@Autowired
	private AppointmentService appService;
	
//	@GetMapping("/client/appointments/{personId}")
//	public List<Appointment> getAppointments(@PathVariable("personId") int personId){
//		return appService.getAppointments(personId);
//	}
//	
//	@GetMapping("/client/appontments/{personId}/{sessionId}")
//	public AppointmentRequest requestAppointment(@PathVariable("personId") int personId, @PathVariable("sessionId") int sessionId) {
//		return appService.requestAppointment(personId, sessionId);
//	}
	
	@PutMapping("/client/appointments/{personId}/{sessionId}")
	public AppointmentRequest editAppointment(@PathVariable("personId") int personId, @PathVariable("sessionId") int sessionId) {
		return appService.editAppointment(personId, sessionId);
	}
}