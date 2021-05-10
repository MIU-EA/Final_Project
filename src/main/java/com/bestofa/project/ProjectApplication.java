package com.bestofa.project;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.bestofa.project.domain.Address;
import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Role;
import com.bestofa.project.domain.Session;
import com.bestofa.project.jms.EmailService;
import com.bestofa.project.repository.AddressRepository;
import com.bestofa.project.repository.PersonRepository;
import com.bestofa.project.repository.RoleRepository;
import com.bestofa.project.repository.SessionRepository;

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
	    EmailService sendEmailService = context.getBean(EmailService.class);

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
			personRepository.save(person);
			sessionRepository.save(new Session(LocalDate.now(), LocalTime.now(), i + 5, person, address));
		}
		sendEmailService.sendEmail();
	}

}
