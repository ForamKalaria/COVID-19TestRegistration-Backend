package com.example.model;


import java.io.Serializable;
import java.sql.Time;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "APPOINTMENT")
public class Appointment implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Id @Column(name = "APP_ID") 
    private int appId;
	
	@ManyToOne
	@JoinColumn(name = "CENTERID",nullable=true)
	private Admin centerId;
	
	@ManyToOne
	@JoinColumn(name="EMPID")
	private Employee empId;
	
	@ManyToOne //(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="EMAIL")
	private Patient email; 
    
	@Column (name = "APP_DATE")
	private LocalDate appointmentDate;
	
	@Column (name ="APP_TIME")
    private Time appointmentTime;
	
	@Column (name ="APP_STATUS")
    private AppointmentStatus status= AppointmentStatus.Booked;

    public Appointment() {

    }

    public Appointment(int appId, Admin center_id, Employee empId, Patient email, LocalDate appointmentDate, Time appointmentTime, AppointmentStatus status) {
        this.appId = appId;
        this.centerId = centerId;
        this.empId = empId;
        this.email = email;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        
    }

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public Admin getCenter_id() {
		return centerId;
	}

	public void setCenter_id(Admin centerId) {
		this.centerId = centerId;
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public Patient getEmail() {
		return email;
	}

	public void setEmail(Patient email) {
		this.email = email;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Time getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	

   
}
