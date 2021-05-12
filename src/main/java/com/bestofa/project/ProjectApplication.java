package com.bestofa.project;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bestofa.project.domain.Address;
import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Role;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.PersonRepository;
import com.bestofa.project.repository.RoleRepository;
import com.bestofa.project.repository.SessionRepository;

@SpringBootApplication
public class ProjectApplication {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@EventListener
	private void runSeeder(ContextRefreshedEvent event) {
		SessionRepository sessionRepository = context.getBean(SessionRepository.class);
		PersonRepository personRepository = context.getBean(PersonRepository.class);
		RoleRepository roleRepository = context.getBean(RoleRepository.class);

		Map<String, Role> map = new HashMap<String, Role>();

		String[] roleNames = { "Admin", "Customer", "Provider" };
		for (String roleName : roleNames) {
			Role role = new Role(roleName);
			roleRepository.save(role);
			map.put(role.getName(), role);
		}

		String[] names = {
			"Alperen",
			"Abyalew",
			"Anuj",
			"Abraham",
			"Anas"
		};
		String[] surnames = {
			"Elbasan",
			"Ambaneh",
			"Aryal",
			"Abrea",
			"Essebani",
		};
		String[] emails = {
			"aelbasan@miu.edu",
			"aambaneh@miu.edu",
			"anujaryal@miu.edu",
			"aabrea@miu.edu",
			"aessenabani@miu.edu"
		};

		Person admin = new Person("Super", "Admin", null, "admin", encoder.encode("admin"), map);
		personRepository.save(admin);
		
		for (int i = 0; i < 10; i++) {
			Person person = new Person(names[i % names.length], surnames[i % surnames.length], emails[i % emails.length], "username" + i, encoder.encode("123456"), map);
			Address address = new Address("52557", "1000 N 4th Street", "Fairfield", "IA", "USA");
			personRepository.save(person);
			LocalDate date = LocalDate.now().plusDays(i + 5);
			
			sessionRepository.save(new Session(date, date.atTime(i % 24, i * 34 % 60).toLocalTime(), i + 5, person, address));
		}

		//sendEmailService.sendEmail();
	}

}
