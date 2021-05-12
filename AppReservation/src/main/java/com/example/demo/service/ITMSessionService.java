package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.domain.TMSession;

public interface ITMSessionService {

	public void deleteSession(Integer id);

	public List<TMSession> getAllSessions();

	public TMSession getSessionById(Integer sessionId, TMSession tmSessionDetails);

	public List<TMSession> getAllFutureSessions();

	public ResponseEntity<Collection<TMSession>> getAllSessionsForProvider(Integer id);

	public ResponseEntity<TMSession> postProviderSession(TMSession tmSession, Integer id);
}
