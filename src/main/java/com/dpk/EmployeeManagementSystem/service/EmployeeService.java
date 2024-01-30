package com.dpk.EmployeeManagementSystem.service;

import java.util.List;

import com.dpk.EmployeeManagementSystem.model.Employee;

public interface EmployeeService {

	void addEmp(Employee emp);

	void deleteEmp(Long id);

	void updateEmp(Employee emp);

	List<Employee> getAllEmp();

	Employee getEmpById(Long id);

	Employee getEmpByEmail(String email);

	Employee getEmpByPhone(String phone);

}
