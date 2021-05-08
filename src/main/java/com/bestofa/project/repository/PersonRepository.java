package com.bestofa.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestofa.project.domain.Person;
import com.bestofa.project.domain.Session;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
}
