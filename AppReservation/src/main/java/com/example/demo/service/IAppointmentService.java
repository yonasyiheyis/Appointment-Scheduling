package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;

public interface IAppointmentService {

	public String deleteAppointment(int id);

	public List<Appointment> getAppointments(int personId);

	public AppointmentRequest requestAppointment(int personId, int sessionId);

	public Appointment approveRequest(int appointmentRequestId);

}
