package com.dpk.EmployeeManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpk.EmployeeManagementSystem.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmailAndPassword(String email, String psw);

	User findByEmail(String email);

	//void save(String password);

}
