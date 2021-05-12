package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.domain.TMSession;
import com.example.demo.repository.TMSessionRepository;

@Service
public class TMSessionService implements ITMSessionService {

	@Autowired
	TMSessionRepository tmSessionRepository;

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

	public TMSession getSessionById(Integer sessionId, TMSession tmSessionDetails) {
		TMSession tmSession = tmSessionRepository.findById(sessionId).orElse(null);
		tmSession.setDuration(tmSessionDetails.getDuration());
		tmSession.setSessionDate(tmSessionDetails.getSessionDate());
		tmSession.setStartTime(tmSessionDetails.getStartTime());
		tmSession.setAddress(tmSessionDetails.getAddress());
		TMSession updatedTMSession = tmSessionRepository.save(tmSession);
		return updatedTMSession;
	}

}
