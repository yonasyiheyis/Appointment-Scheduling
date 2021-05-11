package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.TMSession;

@Repository
@Transactional

public interface TMSessionRepository extends JpaRepository<TMSession, Integer> {
	
}
