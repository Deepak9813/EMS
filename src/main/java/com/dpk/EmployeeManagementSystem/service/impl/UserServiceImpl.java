package com.dpk.EmployeeManagementSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpk.EmployeeManagementSystem.model.User;
import com.dpk.EmployeeManagementSystem.repository.UserRepository;
import com.dpk.EmployeeManagementSystem.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void userSignup(User user) {
		
		userRepo.save(user);
		
	}

	@Override
	public User userLogin(String email, String psw) {
		
		return userRepo.findByEmailAndPassword(email, psw);
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepo.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
		userRepo.save(user);
		
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		
		return userRepo.findById(id).get();
	}

	


}
