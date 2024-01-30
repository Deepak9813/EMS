package com.dpk.EmployeeManagementSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpk.EmployeeManagementSystem.model.Department;
import com.dpk.EmployeeManagementSystem.repository.DepartmentRepository;
import com.dpk.EmployeeManagementSystem.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository deptRepo;

	@Override
	public void addDept(Department dept) {
		// TODO Auto-generated method stub
		
		deptRepo.save(dept);
		
	}

	@Override
	public void deleteDept(int id) {
		// TODO Auto-generated method stub
		
		deptRepo.deleteById(id);
		
	}

	@Override
	public void updateDept(Department dept) {
		// TODO Auto-generated method stub
		
		deptRepo.save(dept);
		
	}

	@Override
	public List<Department> getAllDept() {
		// TODO Auto-generated method stub
		
		return deptRepo.findAll();
	}

	@Override
	public Department getDeptById(int id) {
		// TODO Auto-generated method stub
		
		return deptRepo.findById(id).get();
	}

	@Override
	public Department getDeptByDeptName(String deptName) {
		// TODO Auto-generated method stub
		
		return deptRepo.findByDeptName(deptName);
	}

}
