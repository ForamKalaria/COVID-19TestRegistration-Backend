package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Patient;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	void deletePatientByEmail(String email);

	Optional<Patient> findByEmail(String email);




}