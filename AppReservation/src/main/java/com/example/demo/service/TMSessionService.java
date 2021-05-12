package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Person;
import com.example.demo.domain.RoleType;
import com.example.demo.domain.TMSession;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TMSessionRepository;

@Service
public class TMSessionService implements ITMSessionService {

	@Autowired
	TMSessionRepository tmSessionRepository;
	
	@Autowired
	PersonRepository provider;

	public void deleteSession(Integer id) {
		TMSession tmSession = tmSessionRepository.findById(id).get();
		if (tmSession != null && tmSession.getSessionDate().compareTo(LocalDate.now()) > 1)
			tmSessionRepository.deleteById(id);
	}

	@Override
	public List<TMSession> getAllSessions() {
		return tmSessionRepository.findAll();
	}

	public TMSession getSessionById(Integer sessionId) {
		return tmSessionRepository.findById(sessionId).orElse(null);
	}
	
	// get session by provider - simba
	
	@Override
	public ResponseEntity<Collection<TMSession>> getAllSessionsForProvider(Integer id) {
	Optional<Person> personOptional = provider.findById(id);
	if(personOptional.isEmpty())
		return ResponseEntity.unprocessableEntity().build();
	
	Collection<RoleType> roles = new ArrayList<RoleType>();
	Person person = personOptional.get();
	if(person != null) 
		roles = person.getRoles();		
	
	if(roles.contains(RoleType.Counselor))
		return ResponseEntity.ok()
		        .body(person.getTmSessions()) ;
	return ResponseEntity.badRequest().build();
	
}

// update the session 

	public TMSession getSessionById(Integer sessionId, TMSession tmSessionDetails) {
		TMSession tmSession = tmSessionRepository.findById(sessionId).orElse(null);
		tmSession.setDuration(tmSessionDetails.getDuration());
		tmSession.setSessionDate(tmSessionDetails.getSessionDate());
		tmSession.setStartTime(tmSessionDetails.getStartTime());
		tmSession.setAddress(tmSessionDetails.getAddress());
		TMSession updatedTMSession = tmSessionRepository.save(tmSession);
		return updatedTMSession;
	}

	// client get future service - saurab

	public List<TMSession> getAllFutureSessions() {
		List<TMSession> t = tmSessionRepository.findAll();
		List<TMSession> sessionList = new ArrayList<>();
		for (TMSession tms : t) {
			LocalDate sessionDate = tms.getSessionDate();
			LocalTime sessionTime = tms.getStartTime();
			if (LocalDate.now().compareTo(sessionDate) < 1 && LocalTime.now().compareTo(sessionTime) < 1) {
				sessionList.add(tms);
			}
		}
		return sessionList;
	}
	
	// post session by simba 
	
	@Override
	public ResponseEntity<TMSession> postProviderSession(TMSession tmSession, Integer id) {
		System.out.println("hellos");
		if(tmSession.getSessionDate() == null)
			return ResponseEntity.unprocessableEntity().build();
		
		Optional<Person> personOptional = provider.findById(id);
		if(personOptional.isEmpty())
			return ResponseEntity.unprocessableEntity().build();
		
		Person person = personOptional.get();
		tmSession.setCounselor(person);
		
		if(LocalDate.now().compareTo(tmSession.getSessionDate()) < 1)
			return ResponseEntity.ok()
			         .body(tmSessionRepository.saveAndFlush(tmSession));
		return ResponseEntity.badRequest().build();
		
	}

	

}
