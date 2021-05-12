package com.example.demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.TMSessionController;
import com.example.demo.domain.Address;
import com.example.demo.domain.Appointment;
import com.example.demo.domain.AppointmentRequest;
import com.example.demo.domain.Person;
import com.example.demo.domain.RoleType;
import com.example.demo.domain.TMSession;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TMSessionRepository;

@Component
public class StartupRunner implements CommandLineRunner {

	@Autowired
	private PersonRepository perosn;
	@Autowired
	private TMSessionRepository tmsess;

	@Override

	@Transactional
	public void run(String... args) throws Exception {

		Person per = new Person();
		per.setFirstName("abc");
		per.setLastName("xyz");
		per.setEmailAddress("abc");
		per.setUsername("test");
		per.setPassword("$2a$10$RK3We6QN.noAhbcysAUVbOXlbhTxgwOVAB43csYxB9E2HPJCuUQs.");
		List<RoleType> roles = new ArrayList();
		roles.add(RoleType.Admin);
		roles.add(RoleType.Customer);
		roles.add(RoleType.Counselor);
		per.setRoles(roles);

		TMSession ses = new TMSession();
		ses.setAddress(new Address("abc", "abc", "abc", 1));
		ses.setSessionDate(LocalDate.now());
		ses.setStartTime(LocalTime.now());
		ses.setDuration(30);
		ses.setCounselor(per);

		List<TMSession> tmSessions = new ArrayList();
		tmSessions.add(ses);
		per.setTmSessions(tmSessions);

		Appointment appoint = new Appointment();
		appoint.setPerson(per);
		appoint.setTmSession(ses);

		ses.setAppointment(appoint);

		List<AppointmentRequest> apps = new ArrayList();
		AppointmentRequest appReqs = new AppointmentRequest();
		appReqs.setPerson(per);
		appReqs.setTmSession(ses);

		apps.add(appReqs);

		ses.setAppointmentRequest(apps);

		perosn.save(per);
//		tmsess.save(ses);

	}
}
