package com.example.model;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;


@Entity
@Table(name = "CENTERS")
public class Admin implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	static Logger log = Logger.getLogger(Admin.class.getName());
	
	public static void main(String[] args)throws IOException,SQLException{ 
	 log.debug("Hello this is a debug message");  
     log.info("Hello this is an info message"); 
	}
	
	@Id @Column(name="CENTERID")
	private Integer centerId;
	
	@Column(name="CENTER_LOCATION")
	private String center_location;

//	@ManyToOne(orphanRemoval=false, mappedBy="center_id")
//	@JsonManagedReference
//	private List<Appointment> appList;
//	
	public Admin() {
		super();
	}
	
	public Admin(Integer centerId, String center_location) {
		this.centerId = centerId;	
		this.center_location = center_location;
	}


	public Integer getCenter_id() {
		return centerId;
	}

	public void setCenter_id(Integer centerId) {
		this.centerId = centerId;
	}

	public String getCenter_location() {
		return center_location;
	}

	public void setCenter_location(String center_location) {
		this.center_location = center_location;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return centerId+":"+center_location;
	}
	
	
}
