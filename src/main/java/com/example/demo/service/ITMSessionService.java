package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.TMSession;

public interface ITMSessionService {

	public void deleteSession(Integer id);

	public List<TMSession> getAllSessions();

	public TMSession getSessionById(Integer sessionId, TMSession tmSessionDetails);

}
