package com.example.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.service.PatientService;
import com.example.exception.RecordNotFoundException;
import com.example.model.Patient;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/patient")
public class PatientController {


    @Autowired
    PatientService pservice;
 
	@GetMapping(produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('MODERATOR')")
	public ResponseEntity<List<Patient>> getAllPatient() {
		System.out.println(pservice);
		List<Patient> patientList = pservice.getAllPatient();
		
		System.out.println(patientList);
 
		return new ResponseEntity<List<Patient>>(patientList, new HttpHeaders(), HttpStatus.OK);
    }
 
	@GetMapping(path = "/get/{email}", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public ResponseEntity<Patient> getPatientByEmail(
			@PathVariable("email") String email)
                                                    throws RecordNotFoundException {
		Patient entity = pservice.getPatientByEmail(email);

		return new ResponseEntity<Patient>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping(path = "/insert", produces = "application/json")
    @PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public ResponseEntity<Patient> createOrUpdatePatient(
			@RequestBody Patient patient)
                                                    throws RecordNotFoundException {
		System.out.println(patient);
		Patient updated = pservice.createOrUpdatePatient(patient);
		return new ResponseEntity<Patient>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
	@Transactional
    @DeleteMapping("/{email}")
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public HttpStatus deletePatientByEmail(@PathVariable("email") String email)
                                                    throws RecordNotFoundException {
        pservice.deletePatientByEmail(email);
        return HttpStatus.FORBIDDEN;
    }
 
}