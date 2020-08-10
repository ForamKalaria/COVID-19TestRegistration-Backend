package com.example.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name = "EMPLOYEES")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @Column (name="EMPID")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	
		
	@Column(name="EMPNAME")
	private String empName;
	
	@Column(name="EMPEMAIL")
	private String empEmail;
	
	@Column(name="EMPCONTACT")
	private String empContact;
	
	@Column(name="EMPTYPE")
	private String empType;
	
	@ManyToOne
	@JoinColumn(name ="CENTERID")
	private Admin centerId;
	
	public Employee(int empId, Admin center_id, String empName, String empEmail, String empContact, String empType) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empContact = empContact;
		this.empType = empType;
		this.centerId = centerId;
	}
	public Employee() {
		super();
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpContact() {
		return empContact;
	}
	public void setEmpContact(String empContact) {
		this.empContact = empContact;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public Admin getCenter_id() {
		return centerId;
	}
	public void setCenter_id(Admin centerId) {
		this.centerId = centerId;
	}
	
	
	
}
