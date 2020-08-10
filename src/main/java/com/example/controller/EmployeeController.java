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

import com.example.exception.RecordNotFoundException;
import com.example.model.Admin;
import com.example.model.Employee;
import com.example.service.EmployeeService;


@CrossOrigin(origins="http://localhost:4200")
//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/employees")

public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	
	@GetMapping(produces = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> list = empService.getAllEmployee();
 
		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
    }
	
	
	
	@PostMapping(path="/update", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Employee> createOrUpdateEmployee(
			@RequestBody Employee employee)
                                                    throws RecordNotFoundException {
		System.out.println(employee);
		Employee updated = empService.createOrUpdateEmployee(employee);
		return new ResponseEntity<Employee>(updated, new HttpHeaders(), HttpStatus.OK);
    }
	
	
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<Employee> getEmployeeById(
			@PathVariable("id") Integer empId)
                                                    throws RecordNotFoundException {
		Employee entity = empService.getEmployeeById(empId);

		return new ResponseEntity<Employee>(entity, new HttpHeaders(), HttpStatus.OK);
    }
	

	@DeleteMapping(path="/delete/{id}") 
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public void deleteEmployee(@PathVariable("id") int id) {
		empService.deleteEmployee(id);
	}
	


	
}
