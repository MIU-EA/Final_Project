package com.bestofa.project;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Session;
import com.bestofa.project.repository.SessionRepository;

@SpringBootApplication
public class ProjectApplication {

	
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(ProjectApplication.class, args);
		
		runSeeder(context);
	}
	
	private static void runSeeder(ConfigurableApplicationContext context) {
		SessionRepository sessionRepository = context.getBean(SessionRepository.class);

		for (int i = 0; i < 10; i++) {
			sessionRepository.save(
					new Session(
							LocalDate.now(),
							LocalTime.now(),
							i + 5,
							2,
							new Person("Alperen", "Elbasan", "aalperenelbasan@gmail.com", "123456")
					)
			);
		}
	}

}
