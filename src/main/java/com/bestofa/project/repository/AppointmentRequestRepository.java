package com.bestofa.project.repository;

import com.bestofa.project.domain.Appointment;
import com.bestofa.project.domain.AppointmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Integer> {

}
