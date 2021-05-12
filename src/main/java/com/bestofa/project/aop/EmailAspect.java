package com.bestofa.project.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.jms.EmailService;

@Aspect
@Component
public class EmailAspect {
	@Autowired
	EmailService emailService;
	@AfterReturning(value = "execution(* com.bestofa.project.service.AppointmentService.saveOrUpdateappointment(..)) and args(appointment)")
	public void sendConfiramtionEmail(JoinPoint joinpoint, Appointment appointment) {
		String toRequested = appointment.getRequestor().getEmail();
		String toProvider = appointment.getSession().getCounselor().getEmail();
		List<String> list = Arrays.asList(toRequested, toProvider);
		new Thread() {
			public void run() {
				emailService.sendEmail(list, appointment);			}
		}.start();

	}

}
