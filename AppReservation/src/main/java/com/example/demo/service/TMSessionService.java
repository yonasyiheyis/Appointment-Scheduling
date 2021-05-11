package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TMSession;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TMSessionRepository;

@Service
public class TMSessionService implements ITMSessionService {
	
	@Autowired
	private TMSessionRepository tmSessionRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
//	public List<TMSession> getAllSessions(){
//		return tmSessionRepository.findAll();
//	}
	
	public List<TMSession> getAllFutureSessions(){
		List<TMSession> t = tmSessionRepository.findAll();
		List<TMSession> sessionList = new ArrayList<>();
		
		for(TMSession tms: t) {
			LocalDate sessionDate = tms.getSessionDate();
			
			if(LocalDate.now().compareTo(sessionDate) < 1) {
				sessionList.add(tms);
			}
		}
		return sessionList;
	}
	
//	public TMSession getById(Integer id){
//		return tmSessionRepository.findById(id).get();
//	}
}
