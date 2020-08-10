package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.RecordNotFoundException;
import com.example.model.Appointment;

import com.example.model.Admin;
import com.example.repository.AppointmentRepository;


@Service
public class AppointmentService {
	
	@Autowired
	AppointmentRepository arepository;
	
	
	public List<Appointment> getAllAppointment()
    {
		List<Appointment> appointmentList = (List<Appointment>) arepository.findAll();
         
        if(appointmentList.size() > 0) {
            return appointmentList;
        } else {
			return new ArrayList<Appointment>();
        }
    }
	
	
	public Appointment CreateAppointment(Appointment a) throws RecordNotFoundException
    {
		Optional<Appointment> appointment = arepository.findById(a.getAppId());
		System.out.println(a.getAppId());
		System.out.println(a.getCenter_id());
		System.out.println(a.getEmpId());
		System.out.println(a.getEmail());
		System.out.println(a.getAppointmentDate());
		System.out.println(a.getAppointmentTime());
		System.out.println(a.getStatus());
		
		
        if(appointment.isPresent())
        {
        	Appointment newEntity = appointment.get();
			newEntity.setAppId(a.getAppId());
			newEntity.setCenter_id(a.getCenter_id());
			newEntity.setEmpId(a.getEmpId());
			newEntity.setEmail(a.getEmail());
			newEntity.setAppointmentDate(a.getAppointmentDate());
			newEntity.setAppointmentTime(a.getAppointmentTime());
			newEntity.setStatus(a.getStatus());
			
            newEntity = arepository.save(newEntity);
             
            return newEntity;
        } else {

			a = arepository.save(a);
			return a;
        }
    }

	public Appointment UpdateAppointment(Appointment a) throws RecordNotFoundException
    {
		Optional<Appointment> appointment = arepository.findById(a.getAppId());
		System.out.println(a.getAppId());
		System.out.println(a.getCenter_id());
		System.out.println(a.getEmpId());
		System.out.println(a.getEmail());
		System.out.println(a.getAppointmentDate());
		System.out.println(a.getAppointmentTime());
		System.out.println(a.getStatus());
		
		
        if(appointment.isPresent())
        {
//        	Appointment newEntity = appointment.get();
//			newEntity.setAppId(a.getAppId());
//			newEntity.setCenter_id(a.getCenter_id());
//			newEntity.setEmpId(a.getEmpId());
//			newEntity.setEmail(a.getEmail());
//			newEntity.setAppointmentDate(a.getAppointmentDate());
//			newEntity.setAppointmentTime(a.getAppointmentTime());
//			newEntity.setStatus(a.getStatus());
			
//            newEntity = arepository.save(newEntity);
        	
        	 arepository.save(a);
			
             
            return a;//newEntity;
            
        } else {

			//a = arepository.save(a);
			return null;
        }
    }
	
	public Appointment getAppointmentById(Integer appId) throws RecordNotFoundException
    {
		Optional<Appointment> appointment = arepository.findById(appId);
         
        if(appointment.isPresent()) {
            return appointment.get();
        } else {
            throw new RecordNotFoundException("No appointment exist for given id");
        }
    }
	
	public Appointment getAppointmentByCenterId(Integer centerId) throws RecordNotFoundException
    {
		Optional<Appointment> appointment = arepository.findByCenterId(centerId);
         
        if(appointment.isPresent()) {
            return appointment.get();
        } else {
            throw new RecordNotFoundException("No appointment exist for given id");
        }
    }
	
	
	public void deleteAppointment(int appId) {
		arepository.deleteById(appId);
	}


//	public List<Admin> findByCenterId(Integer centerId) throws RecordNotFoundException
//    {
//		Optional<List<Admin>> appointment = arepository.findByCenterId(centerId);
//         
//        if(appointment.isPresent()) {
//            return appointment.get();
//        } else {
//            throw new RecordNotFoundException("No appointment exist for given id");
//        }
//    }


}


	
	

