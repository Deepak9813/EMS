package com.dpk.EmployeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dpk.EmployeeManagementSystem.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

	Department findByDeptName(String deptName);

	
}
