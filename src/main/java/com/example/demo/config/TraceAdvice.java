package com.example.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAdvice {
		
	@Before("execution(*  com.example.demo.service.AppointmentService.*(..)) && args(personId, sessionId)")
	public void tracebeforemethod(JoinPoint joinpoint, Integer personId, Integer sessionId) {
		System.out.print("before execution of method " + joinpoint.getSignature());
		
	}

	@AfterReturning(pointcut = "execution(* com.example.demo.service.AppointmentService.*(..))", returning = "result")
	public void traceaftermethodNormal(JoinPoint joinpoint, boolean result) {
		System.out.println("after normal execution of method " + joinpoint.getSignature().getName());
		System.out.println("execution was successful? " + result);
	}

	@AfterThrowing(pointcut = "execution(* com.example.demo.service.AppointmentService.*(..))", throwing = "exception")
	public void traceaftermethodException(JoinPoint joinpoint, Exception exception) {
		System.out.println("after execution of method " + joinpoint.getSignature().getName());
		System.out.println("following exception happenned " + exception);
	}
}