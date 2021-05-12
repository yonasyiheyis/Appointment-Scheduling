package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Appointment;
import com.example.demo.service.IEmailService;

@Aspect
@Component
public class EmailAdvice {

	@Autowired
	private IEmailService emailService;

	@AfterReturning(pointcut = "execution(* com.example.demo.service.AppointmentService.approveRequest(..))", returning = "appointment")
	public void traceaftermethodNormal(JoinPoint joinpoint, Appointment appointment) {
		
		if (appointment == null) return;
		
		final String subject = "Appointment Approval";
		String text = "";
		String toCustomer = appointment.getPerson().getEmailAddress();
		String toCounselor = appointment.getTmSession().getCounselor().getEmailAddress();

		text += "Appointment Approved!\n";
		text += "Attendee name: " + appointment.getPerson().getFirstName() + "\n";
		text += "Appointment Date: " + appointment.getTmSession().getSessionDate() + "\n";
		text += "Appointment Time: " + appointment.getTmSession().getStartTime() + "\n";
		text += "Appointment Duration: " + appointment.getTmSession().getDuration() + " mins\n";
		text += "Appointment Address: " + appointment.getTmSession().getAddress().toString() + "\n";
		text += "Counselor: " + appointment.getPerson().getFirstName() + "\n";

		emailService.sendSimpleMessage(toCustomer, subject, text);
		emailService.sendSimpleMessage(toCounselor, subject, text);

	}

}
