package com.dpk.EmployeeManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_tbl")
public class User {
	
	@Id														//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//AI[auto]
	private int id;
	private String fname;
	private String lname;
	private String userName;
	private String email;
	private String password;

}
