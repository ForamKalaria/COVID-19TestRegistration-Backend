package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.RecordNotFoundException;
import com.example.model.Admin;
import com.example.repository.AdminRepository;

 
@Service
public class AdminService {
     
    @Autowired
    AdminRepository repository;
    
     
	public List<Admin> getAllCenters()
    {
		List<Admin> centerList = repository.findAll();
         
        if(centerList.size() > 0) {
            return centerList;
        } else {
			return new ArrayList<Admin>();
        }
    }
     
	public Admin getCentersById(Integer center_id) throws RecordNotFoundException
    {
		Optional<Admin> center = repository.findById(center_id);
         
        if(center.isPresent()) {
            return center.get();
        } else {
            throw new RecordNotFoundException("No center exist for given id");
        }
    }
     
	public Admin createOrUpdateCenter(Admin c) throws RecordNotFoundException
    {
		Optional<Admin> center = repository.findById(c.getCenter_id());
		System.out.println(c.getCenter_id());
		System.out.println(c.getCenter_location());
		
        if(center.isPresent())
        {
			Admin newEntity = center.get();
			newEntity.setCenter_id(c.getCenter_id());
			newEntity.setCenter_location(c.getCenter_location());
			
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {

			c = repository.save(c);
             
			return c;
        }
    }
     
	public void deleteCenterById(Integer center_id) throws RecordNotFoundException
    {
		Optional<Admin> center = repository.findById(center_id);
         
        if(center.isPresent())
        {
            repository.deleteById(center_id);
        } else {
            throw new RecordNotFoundException("No center record exist for given id");
        }
    }
	
	
}