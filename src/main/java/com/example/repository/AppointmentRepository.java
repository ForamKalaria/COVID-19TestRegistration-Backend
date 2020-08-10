package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Admin;
import com.example.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	Optional<Appointment> findByCenterId(Integer centerId);

	// public Optional<List<Admin>> getAppointmentByCenterId(Admin centerId);

	
}
