package com.dpk.EmployeeManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpk.EmployeeManagementSystem.model.Employee;
import com.dpk.EmployeeManagementSystem.repository.EmployeeRepository;
import com.dpk.EmployeeManagementSystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public void addEmp(Employee emp) {
		// TODO Auto-generated method stub
		
		empRepo.save(emp);
		
	}

	@Override
	public void deleteEmp(Long id) {
		// TODO Auto-generated method stub
		
		empRepo.deleteById(id);
		
	}

	@Override
	public void updateEmp(Employee emp) {
		// TODO Auto-generated method stub
		
		empRepo.save(emp);
		
	}

	@Override
	public List<Employee> getAllEmp() {
		// TODO Auto-generated method stub
		
		return empRepo.findAll();
	}

	@Override
	public Employee getEmpById(Long id) {
		// TODO Auto-generated method stub
		
		return empRepo.findById(id).get();
	}

	@Override
	public Employee getEmpByEmail(String email) {
		// TODO Auto-generated method stub
		
		return empRepo.findByEmail(email);
	}

	@Override
	public Employee getEmpByPhone(String phone) {
		// TODO Auto-generated method stub
		
		return empRepo.findByPhone(phone);
	}

}
