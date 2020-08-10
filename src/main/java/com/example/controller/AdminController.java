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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AdminService;
import com.example.exception.RecordNotFoundException;
import com.example.model.Admin;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/centers")
public class AdminController {

    @Autowired
    AdminService service;
 
	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Admin>> getAllCenters() {
		List<Admin> list = service.getAllCenters();
 
		return new ResponseEntity<List<Admin>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
	@GetMapping(path = "/get/{id}", produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Admin> getCentersById(
			@PathVariable("id") Integer centerId)
                                                    throws RecordNotFoundException {
		Admin entity = service.getCentersById(centerId);

		return new ResponseEntity<Admin>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping(path = "/insert", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Admin> createOrUpdateCenter(
			@RequestBody Admin center)
                                                    throws RecordNotFoundException {
		System.out.println(center);
		Admin updated = service.createOrUpdateCenter(center);
		return new ResponseEntity<Admin>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public HttpStatus deleteCenterById(@PathVariable("id") Integer centerId)
                                                    throws RecordNotFoundException {
        service.deleteCenterById(centerId);
        return HttpStatus.FORBIDDEN;
    }
 
}