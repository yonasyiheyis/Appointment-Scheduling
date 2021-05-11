package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Appointment;
import com.example.demo.service.AppointmentService;

@RestController
@RequestMapping("client/appointments")
public class AppointmentController {

	@Autowired
	AppointmentService service;
	
	@DeleteMapping("/{appointmentid}")
	public String deleteAppointmentId(@PathVariable String appointmentid) {
		service.deleteAppointment(appointmentid);
		return "Successfull appointment by ID = " + appointmentid;
	} 
}
