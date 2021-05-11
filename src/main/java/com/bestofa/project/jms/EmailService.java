package com.bestofa.project.jms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bestofa.project.domain.Appointment;



@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender javaMailSender;
    
    public void sendEmail(List<String>to,Appointment appointment) {
        SimpleMailMessage msg = new SimpleMailMessage();
        String[]addresses =to.stream().toArray(String[]::new);
        msg.setTo(addresses);
        msg.setFrom("essebani.anas@gmail.com");
        
        msg.setSubject("About Your Appointment Status");
        String status=appointment.getStatus().compareTo("Pending")==0? "Created":appointment.getStatus();
        msg.setText("Hello Dear \n the Appointment Number: "+appointment.getId()
        +" has been "+ status+"\n" );

        javaMailSender.send(msg);
        System.err.println("email Sending...");

    }

}
