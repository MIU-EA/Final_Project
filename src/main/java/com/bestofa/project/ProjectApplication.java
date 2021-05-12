package com.bestofa.project;

import java.time.LocalDate;

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

		Role adminRole = new Role("Admin");
		roleRepository.save(adminRole);

		Role customerRole = new Role("Customer");
		roleRepository.save(customerRole);

		Role providerRole = new Role("Provider");
		roleRepository.save(providerRole);

		String[] usernames = { "alperen", "abby", "anuj", "abraham", "anas" };
		String[] names = { "Alperen", "Abyalew", "Anuj", "Abraham", "Anas" };
		String[] surnames = { "Elbasan", "Ambaneh", "Aryal", "Abrea", "Essebani", };
		String[] emails = { "aelbasan@miu.edu", "aambaneh@miu.edu", "anujaryal@miu.edu", "aabrea@miu.edu",
				"aessenabani@miu.edu" };

		Person admin = new Person("Super", "Admin", null, "admin", encoder.encode("admin"), adminRole);
		personRepository.save(admin);

		Person provider = new Person("Prof", "Provider", "1alperenelbasan98@gmail.com", "provider", encoder.encode("provider"), providerRole);
		personRepository.save(provider);

		for (int i = 0; i < usernames.length; i++) {
			Person person = new Person(
					// person name
					names[i % names.length],
					// person surname
					surnames[i % surnames.length],
					// person email
					emails[i % emails.length],
					// person username
					usernames[i],
					// person password,
					encoder.encode("123456"),
					// person role
					customerRole);
			Address address = new Address("52557", "1000 N 4th Street", "Fairfield", "IA", "USA");
			personRepository.save(person);
			LocalDate date = LocalDate.now().plusDays(i + 5);

			sessionRepository
					.save(new Session(date, date.atTime(i % 24, i * 34 % 60).toLocalTime(), i + 5, person, address));
		}

		// sendEmailService.sendEmail();
	}

}
