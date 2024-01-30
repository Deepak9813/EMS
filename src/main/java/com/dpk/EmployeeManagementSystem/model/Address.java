package com.dpk.EmployeeManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "address_tbl")
public class Address {
	
	@Id														//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//AI[auto]
	private int id;
	private String country;
	private String city;
	private String state;
	private String zipcode;

}
