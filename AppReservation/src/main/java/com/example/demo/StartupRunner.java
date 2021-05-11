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
	private PersonRepository person;
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
		per.setPassword("123");
		Set<RoleType> roles = new HashSet<>();
		roles.add(RoleType.Admin);
		roles.add(RoleType.Customer);
		per.setRoles(roles);

		TMSession ses = new TMSession();
		ses.setAddress(new Address("abc", "abc", "abc", 1));
//		ses.setSessionDate(LocalDate.now());
		ses.setSessionDate(LocalDate.of(2021, 5, 4));
		ses.setStartTime(LocalTime.of(9, 45));
		ses.setDuration(30);
		ses.setCounselor(per);

		Appointment appoint = new Appointment();
		appoint.setPerson(per);
		appoint.setTmSession(ses);

		ses.setAppointment(appoint);

		List<AppointmentRequest> apps = new ArrayList<>();
		AppointmentRequest appReqs = new AppointmentRequest();
		appReqs.setPerson(per);
		appReqs.setTmSession(ses);

		apps.add(appReqs);

		ses.setAppointmentRequest(apps);

		person.save(per);
		tmsess.save(ses);

		// new additional data
		TMSession ses2 = new TMSession();
		ses2.setAddress(new Address("abc2", "abc2", "abc2", 2));
		ses2.setSessionDate(LocalDate.of(2021, 5, 24));
		ses2.setStartTime(LocalTime.of(10, 00));
		ses2.setDuration(40);
		ses2.setCounselor(per);

		tmsess.save(ses2);

		// new additional data
		TMSession ses3 = new TMSession();
		ses3.setAddress(new Address("abc3", "abc3", "abc3", 3));
		ses3.setSessionDate(LocalDate.of(2021, 6, 9));
		ses3.setStartTime(LocalTime.of(11, 30));
		ses3.setDuration(40);
		ses3.setCounselor(per);

		tmsess.save(ses3);

	}
}
