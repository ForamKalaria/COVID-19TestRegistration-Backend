package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.RecordNotFoundException;
import com.example.model.Patient;
import com.example.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
    PatientRepository prepository;
	
	public List<Patient> getAllPatient()
    {
		List<Patient> patientList = prepository.findAll();
         
        if(patientList.size() > 0) {
            return patientList;
        } else {
			return new ArrayList<Patient>();
        }
    }
	
	public Patient createOrUpdatePatient(Patient p) throws RecordNotFoundException
    {
		Optional<Patient> patient = prepository.findByEmail(p.getEmail());
		System.out.println(p.getEmail());
		System.out.println(p.getFirstname());
		System.out.println(p.getLastname());
		System.out.println(p.getPassword());
		
        if(patient.isPresent())
        {
			Patient newEntity = patient.get();
			newEntity.setEmail(p.getEmail());
			newEntity.setFirstname(p.getFirstname());
			newEntity.setLastname(p.getLastname());
			newEntity.setPassword(p.getPassword());
			
            newEntity = prepository.save(newEntity);
             
            return newEntity;
        } else {

			p = prepository.save(p);
             
			return p;
        }
    }
	
	public Patient getPatientByEmail(String email) throws RecordNotFoundException
    {
		Optional<Patient> patient = prepository.findByEmail(email);
         
        if(patient.isPresent()) {
            return patient.get();
        } else {
            throw new RecordNotFoundException("No Patient exist for given email");
        }
    }
	
	public void deletePatientByEmail(String email) throws RecordNotFoundException
    {
		Optional<Patient> patient = prepository.findByEmail(email);
         
        if(patient.isPresent())
        {
            prepository.deletePatientByEmail(email);
        } else {
            throw new RecordNotFoundException("No patient record exist for given email");
        }
    } 

}
