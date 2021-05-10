package com.bestofa.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestofa.project.domain.Person;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {
	Person findByUsername(String username);
}
