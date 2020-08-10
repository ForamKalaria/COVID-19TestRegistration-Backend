package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.RecordNotFoundException;
import com.example.model.Admin;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository erepository;
	
	
	public List<Employee> getAllEmployee()
    {
		List<Employee> employeeList = (List<Employee>) erepository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
			return new ArrayList<Employee>();
        }
    }
	
	
	public Employee createOrUpdateEmployee(Employee e) throws RecordNotFoundException
    {
		Optional<Employee> employee = erepository.findById(e.getEmpId());
		System.out.println(e.getEmpId());
		System.out.println(e.getEmpName());
		System.out.println(e.getEmpEmail());
		System.out.println(e.getEmpContact());
		System.out.println(e.getEmpType());
		System.out.println(e.getCenter_id());
		
        if(employee.isPresent())
        {
			Employee newEntity = employee.get();
			newEntity.setEmpId(e.getEmpId());
			newEntity.setEmpName(e.getEmpName());
			newEntity.setEmpEmail(e.getEmpEmail());
			newEntity.setEmpContact(e.getEmpContact());
			newEntity.setEmpType(e.getEmpType());
			newEntity.setCenter_id(e.getCenter_id());
			
            newEntity = erepository.save(newEntity);
             
            return newEntity;
        } else {

			e = erepository.save(e);
             
			return e;
        }
    }
	
	public Employee getEmployeeById(Integer empId) throws RecordNotFoundException
    {
		Optional<Employee> employee = erepository.findById(empId);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee exist for given id");
        }
    }
	
	
	
	public void deleteEmployee(int empId) {
		erepository.deleteById(empId);
	}
//	
//	public Employee findEmpById(int empId) {
//		Employee emp = erepository.findById(empId).get();
//		
//		return emp;
//	}
//	
//	public List<Employee> findAllEmployees() {
//		return (List<Employee>) erepository.findAll();
//	}
//	
	
	
	

}