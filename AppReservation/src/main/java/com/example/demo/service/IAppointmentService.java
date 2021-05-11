package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;

public interface IAppointmentService {

//	public List<Appointment> getAppointments(int personId);
//	
//	public AppointmentRequest getAppointment(int appointmentId);
	
	public AppointmentRequest editAppointment(int personId, int sessionId);
}
