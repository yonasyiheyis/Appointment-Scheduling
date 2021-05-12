package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;
import com.example.demo.service.AppointmentService;

@RestController
public class AppointmentController {

	@Autowired
	AppointmentService appService;

	// deletion by zolzaya

	@DeleteMapping("DELETE/client/appointment/{appointmentid}")
	public String deleteAppointmentId(@PathVariable int appointmentid) {
		return appService.deleteAppointment(appointmentid);

	}

	// completed by yonas

	@GetMapping("GET/client/appointments/{personId}")
	public List<Appointment> getAppointments(@PathVariable("personId") int personId) {
		return appService.getAppointments(personId);
	}

	@PostMapping("POST/client/appontments/{personId}/{sessionId}")
	public AppointmentRequest requestAppointment(@PathVariable("personId") int personId,
			@PathVariable("sessionId") int sessionId) {
		return appService.requestAppointment(personId, sessionId);
	}

	@PostMapping("POST/admin/appointments/{appointmentRequestId}")
	public Appointment approveAppointment(@PathVariable("appointmentRequestId") int appointmentRequestId) {
		return appService.approveRequest(appointmentRequestId);
	}

}
