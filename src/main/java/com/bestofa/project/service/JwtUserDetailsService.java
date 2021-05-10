package com.bestofa.project.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bestofa.project.domain.Person;
import com.bestofa.project.repository.PersonRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Person person = personRepository.findByUsername(username);

			if (person == null)
				throw new UsernameNotFoundException("User not found with username: " + username);

			return new User(person.getUsername(), person.getPassword(), person.getRoles().keySet().stream()
					.map(roleName -> "ROLE_" + roleName).map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		} catch (Exception e) {
			System.err.println(e);
			throw e;
		}
	}
}