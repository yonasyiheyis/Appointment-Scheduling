package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.TMSession;
import com.example.demo.service.TMSessionService;

@RestController
@RequestMapping("")
public class TMSessionController {

	@Autowired
	private TMSessionService tmSessionService;

	// get all session

	@GetMapping("/GET/provider/session/")
	public List<TMSession> getAllSessions() {
		return tmSessionService.getAllSessions();
	}

	// get session by id

	@GetMapping("/GET/provider/session/{sessionId}")
	public TMSession getSessionById(@PathVariable("sessionId") Integer sessionId) {
		return tmSessionService.getSessionById(sessionId);
	}

	// delete session by id

	@DeleteMapping("/DELETE/provider/session/{sessionId}")
	public void deleteSessionById(@PathVariable("sessionId") Integer sessionId) {
		tmSessionService.deleteSession(sessionId);
	}

	// update session by id

	@PutMapping("/PUT/provider/session/{sessionId}")
	public TMSession updateTMSession(@PathVariable(value = "sessionId") Integer sessionId,
			@RequestBody TMSession tmSessionDetails) {
		return tmSessionService.getSessionById(sessionId, tmSessionDetails);
	}
}
