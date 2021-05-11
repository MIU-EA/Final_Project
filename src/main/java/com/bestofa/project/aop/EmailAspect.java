package com.bestofa.project.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import com.bestofa.project.jms.EmailService;

@Aspect
public class EmailAspect {
	@Autowired
	EmailService emailService;
//	
//	@AfterReturning("")
//	public void sendConfiramtionEmail(AppointmentRequest appointmentRequest ) {
//		String toRequested=appointmentRequest.getPersonRequested().getEmail();
//		String toProvider=appointmentRequest.getRequestedSession().getCounselor().getEmail();
//	}
	
	

}
