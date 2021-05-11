package com.example.demo.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.TMSession;
import com.example.demo.service.TMSessionService;

@RestController
public class TMSessionController {

	@Autowired
	TMSessionService tmSessionService;
	
	// extra, not needed, only for practice
//	@GetMapping("/provider/sessions")
//	public List<TMSession> getAllSessions(){
//		return tmSessionService.getAllSessions();
//	}
	
	@GetMapping("/client/sessions")
	public List<TMSession> getAllFutureSessions(){
		return tmSessionService.getAllFutureSessions();
	}
	
	// extra, not needed, only for practice
//	@GetMapping("/provider/sessions/{id}")
//	public TMSession getById(@PathVariable(value="id") Integer id){
//		return tmSessionService.getById(id);
//	}
}
