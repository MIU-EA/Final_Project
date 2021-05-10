package com.bestofa.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bestofa.project.domain.Appointment;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
