package com.dpk.EmployeeManagementSystem.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_tbl")
public class Employee {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AI[auto]
	private Long id;
	private String fname;
	private String lname;
	private String gender;
	private int age;

	@Column(unique = true)
	private String phone;
	@Column(unique = true)
	private String email;

	@DateTimeFormat(iso = ISO.DATE) // YYYY-MM-DD format
	private LocalDate dob;

	private String company;
	private String post;

	@OneToOne
	@JoinColumn(name = "deptId")
	private Department department;

	private String salary;
	@DateTimeFormat(iso = ISO.DATE) // YYYY-MM-DD format
	private LocalDate joinDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId")
	private Address address;

	@ElementCollection
	@CollectionTable
	private List<String> projects;

}
