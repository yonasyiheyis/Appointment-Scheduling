package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService{

	@Autowired
	AppointmentRepository repo;
	@Override
	public void deleteAppointment(String id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	
}
