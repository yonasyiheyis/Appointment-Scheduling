package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;
import com.example.demo.domain.Person;
import com.example.demo.domain.TMSession;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.AppointmentRequestRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TMSessionRepository;

@Service
public class AppointmentService implements IAppointmentService {

	@Autowired
	private PersonRepository personRepo;

	@Autowired
	private AppointmentRepository appointmentRepo;

	@Autowired
	private TMSessionRepository sessionRepo;

	@Autowired
	private AppointmentRequestRepository appRequestRepo;

	@Autowired
	private EmailService emailService;

	public List<Appointment> getAppointments(int personId) {

		return appointmentRepo.findAppointments(personId);

	}

	public AppointmentRequest requestAppointment(int personId, int sessionId) {

		if (appointmentRepo.findAppointments(personId, sessionId) != null)
			return null;

		AppointmentRequest existingRequest = appRequestRepo.findAppointmentRequests(personId, sessionId);
		if (existingRequest != null)
			return existingRequest;

		Person client = personRepo.findById(personId).orElse(null);
		TMSession session = sessionRepo.findById(sessionId).orElse(null);

		if (client == null || session == null)
			return null;

		AppointmentRequest newRequest = new AppointmentRequest();
		newRequest.setPerson(client);
		newRequest.setTmSession(session);

		AppointmentRequest persisted = appRequestRepo.save(newRequest);

		return persisted;

	}

	public Appointment approveRequest(int appointmentRequestId) {

		AppointmentRequest existingRequest = appRequestRepo.findById(appointmentRequestId).orElse(null);

		if (existingRequest == null)
			return null;

		if (appointmentRepo.findAppointments(existingRequest.getPerson().getId(),
				existingRequest.getTmSession().getId()) != null)
			return null;

		Appointment approved = new Appointment();
		approved.setPerson(existingRequest.getPerson());
		approved.setTmSession(existingRequest.getTmSession());

		Appointment approvedAppointment = appointmentRepo.save(approved);

		appRequestRepo.delete(existingRequest);

		return approvedAppointment;

	}

	// delete appointment zolzaya

	@Override
	public String deleteAppointment(int id) {
		// TODO Auto-generated method stub
		int two_days = 60 * 60 * 48;

		/*
		 * Given Appointment id WHEN createdDate less 48 hours THEN Delete current ID,
		 * Save log
		 */

		Optional<AppointmentRequest> appointment = Optional.empty();
		appointment = appRequestRepo.findById(id);
		System.out.println("hello");
		if (appointment.isPresent()) {

			LocalDate expectDate = appointment.get().getTmSession().getSessionDate().minusDays(2);
			LocalDate currentDate = LocalDate.now();
			LocalTime expectedTime = appointment.get().getTmSession().getStartTime();
			LocalTime currentTime = LocalTime.now();

			System.out.println("result::::");
			System.out.println(expectDate);
			System.out.println(currentDate);
			if (currentDate.isBefore(expectDate)
					|| (expectDate.equals(currentDate) && currentTime.isBefore(expectedTime)))
				appRequestRepo.deleteById(id);
			else
				return "Cannot this Appointment, Time over";
		} else
			// cannot cancel connect to Administrotor;
			return "Record not found - 401";

		return "Successfully cancelled ID =  " + String.valueOf(id);
	}

}
