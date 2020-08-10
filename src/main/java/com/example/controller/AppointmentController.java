package com.example.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.RecordNotFoundException;
import com.example.model.Appointment;
import com.example.repository.AppointmentRepository;
import com.example.service.AppointmentService;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/appointment")
public class AppointmentController {
	
	
	@Autowired
	AppointmentService aservice;
	
	@Autowired
	AppointmentRepository arepository;
 
	@GetMapping(produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Appointment>> getAllAppointment() {
		System.out.println(aservice);
		List<Appointment> appointmentList = aservice.getAllAppointment();
		
		System.out.println(appointmentList);
 
		return new ResponseEntity<List<Appointment>>(appointmentList, new HttpHeaders(), HttpStatus.OK);
    }

	
	@PostMapping(path = "/insert", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public ResponseEntity<Appointment> CreateAppointment(
			@RequestBody Appointment appointment)
                                                    throws RecordNotFoundException {
		System.out.println(appointment);
				
	//	System.out.println("AppID => " +appointment.getAppId());
	//	System.out.println("CenterID => " +appointment.getCenter_id());
		
		Appointment created = aservice.CreateAppointment(appointment);
		return new ResponseEntity<Appointment>(created, new HttpHeaders(), HttpStatus.OK);
    }
	
	
	
	@PutMapping(path = "/update/{id}", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public ResponseEntity<Appointment> UpdateAppointment(
		    @PathVariable(value = "id") int appId,
		   @RequestBody Appointment appointment) throws RecordNotFoundException {
			Appointment entity = aservice.getAppointmentById(appId);
		          //.orElseThrow(() -> new ResourceNotFoundException("User not found on :: "+ appId));
	        
			final Appointment updated = arepository.save(appointment);
		        
		        return new ResponseEntity<Appointment>(updated, new HttpHeaders(), HttpStatus.OK);
		   }

	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public ResponseEntity<Appointment> getAppointmentById(
			@PathVariable("id") Integer appId)
                                                    throws RecordNotFoundException {
		Appointment entity = aservice.getAppointmentById(appId);

		return new ResponseEntity<Appointment>(entity, new HttpHeaders(), HttpStatus.OK);
    }
	
	@GetMapping(path = "/center/{id}", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public ResponseEntity<Appointment> getAppointmentByCenterId(
			@PathVariable("id") Integer centerId)
                                                    throws RecordNotFoundException {
		Appointment entity = aservice.getAppointmentByCenterId(centerId);

		return new ResponseEntity<Appointment>(entity, new HttpHeaders(), HttpStatus.OK);
    }
	
	
	
	@DeleteMapping(path="/{id}") 
	@PreAuthorize("hasRole('MANAGER') or hasRole('USER') or hasRole('MODERATOR')")
	public void deleteEmployee(@PathVariable("id") int appId) {
		aservice.deleteAppointment(appId);
	}

}
