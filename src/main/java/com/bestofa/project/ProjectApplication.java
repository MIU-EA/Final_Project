package com.bestofa.project;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.bestofa.project.domain.*;
import com.bestofa.project.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ProjectApplication.class, args);

		runSeeder(context);
	}

	private static void runSeeder(ConfigurableApplicationContext context) {
		SessionRepository sessionRepository = context.getBean(SessionRepository.class);
		PersonRepository personRepository = context.getBean(PersonRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		AddressRepository addressRepository = context.getBean(AddressRepository.class);

		AppointmentRepository appointmentRepository = context.getBean(AppointmentRepository.class);
		AppointmentRequestRepository appointmentRequestRepository = context.getBean(AppointmentRequestRepository.class);


		Map<String, Role> map = new HashMap<String, Role>();

		String[] roleNames = { "Admin", "Customer", "Provider" };
		for (String roleName : roleNames) {
			Role role = new Role(roleName);
			roleRepository.save(role);
			map.put(role.getName(), role);
		}

		for (int i = 0; i < 10; i++) {

			Person person = new Person("Alperen", "Elbasan", "aalperl.com", "username", "123456", map);
			Address address = new Address("52557", "1000 N 4th Street", "Fairfield", "IA", "USA");
			if (i % 2 == 0) {
				sessionRepository.save(new Session(LocalDate.now(), LocalTime.now(), i + 5, person, address));

			} else {
				sessionRepository.save(new Session(LocalDate.now().plusDays(1), LocalTime.now(), i + 5, person, address));

			}
		}
		Session session = sessionRepository.findById(1).get();
		Session session2 = sessionRepository.findById(2).get();
		Person customer =  personRepository.findById(1).get();
		Person customer2 =  personRepository.findById(2).get();
		for(int i = 0; i <5 ; i++) {
			if(i %2== 0){
				appointmentRequestRepository.save(new AppointmentRequest(customer, session));
			}
			else{
				appointmentRequestRepository.save(new AppointmentRequest(customer2, session2));
			}
		}
	}

}
