package com.dpk.EmployeeManagementSystem.service;

import com.dpk.EmployeeManagementSystem.model.User;

public interface UserService {

	void userSignup(User user);

	User userLogin(String email, String psw);

	User getUserById(int id);

	User getUserByEmail(String email);

//	========== forgot password ko laagi ================
	// User getUserByEmail(String email); //already mathi banaya raixu
	// User getUserByEmailAndPhone(Sring email, String phone); // yesari garda ni
	// hunxa

	void updateUser(User user);

}
