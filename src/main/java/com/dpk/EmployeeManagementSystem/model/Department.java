package com.dpk.EmployeeManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "department_tbl")
public class Department {

	@Id																//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)				//AI[auto]
	private int id;
	private String deptName;
	private String deptHead;
	private String deptPhone;
}
