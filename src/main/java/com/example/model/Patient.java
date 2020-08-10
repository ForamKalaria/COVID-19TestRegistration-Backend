package com.example.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

	
@Entity
@Table(name = "patient")
public class Patient implements Serializable {
		
	private static final long serialVersionUID = 1L;
		
	@Id @Column(name="EMAIL")
	private String email; 
		
	@Column(name="FIRSTNAME")
	private String firstname;
	
	@Column(name="LASTNAME")
	private String lastname;
	
	@Column(name="PASSWORD")
	private String password;
	
	
	
	
//		@OneToMany(orphanRemoval=true, mappedBy="p")
//		@JsonManagedReference
//		private List<Patient> patientList;
	
		public Patient(String email, String firstname, String lastname, String password) {
			super();
			this.email = email;
			this.firstname = firstname;
			this.lastname = lastname;
			this.password = password;
		}
		public Patient() {
			super();
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFirstname() {
			return firstname;
		}
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}
		public String getLastname() {
			return lastname;
		}
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return email+":"+firstname+":"+lastname;
		}
		

}
