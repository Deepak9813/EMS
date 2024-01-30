package com.dpk.EmployeeManagementSystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dpk.EmployeeManagementSystem.model.User;
import com.dpk.EmployeeManagementSystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/login" })
	public String getLogin() {

		return "LoginForm";
	}

	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session) {

		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		User usr = userService.userLogin(user.getEmail(), user.getPassword());

		if (usr != null) {

			log.info("=============== login success ==================");

			session.setAttribute("validUser", usr); // ("key", value)
			session.setMaxInactiveInterval(500);
			// 500 sec samma ideal basyo vanyo vanye aafai logout hunxa

			model.addAttribute("uname", usr.getUserName()); // ("key", value)
			return "Home";
		}

		log.info("================= login failed =======================");

		model.addAttribute("errorMessage", "user doesn't exist!..."); // ("key", value)
		return "LoginForm";
	}

	@GetMapping("/signup")
	public String getSignup() {

		return "SignupForm";
	}

	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user, @RequestParam String confirmPassword, Model model) {

		//yo password and confirm password javascript(or jQuery) baata garda ni hunxa. ie. front end ma
		//yo maile backend ma validation gareko
		if ((user.getPassword() != confirmPassword) && (!user.getPassword().equals(confirmPassword))) {

			model.addAttribute("message", "Password doesn't match!..."); // ("key", value)

			return "SignupForm";
		}

		// check user(email) already exist or not
		User usr = userService.getUserByEmail(user.getEmail());
		if (usr != null) {

			model.addAttribute("message", "user(email) already exist!..."); // ("key", value)

			return "SignupForm";
		}

		log.info("============ signup success ======================");

		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userService.userSignup(user);

		// model.addAttribute("message", "Signup Success"); //("key", value)
		// return "LoginForm";
		return "redirect:/login"; // GetMapping wala call hunxa redirect le
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {

		log.info("=============== user logout success ==================");

		session.invalidate(); // session kill gareko

		return "LoginForm";
	}

	// getProfile = profile matra lekhda ni hunxa, j lekhda ni hunxa
	@GetMapping("/profile")
	public String getProfile(HttpSession session) {

		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

		return "Profile";
	}

// =================== for Forgot Password =========================	

	@GetMapping("/forgotPassword")
	public String getForgotPasswordForm() {

		return "ForgotPasswordForm";
	}

	@PostMapping("/forgotPassword")
	public String postForgotPasswordForm(@ModelAttribute User user, Model model) {

		User usr = userService.getUserByEmail(user.getEmail());

		if (usr != null) {

			// return "ChangePasswordForm";
			
			// ============= Password Change garda id lagera(id controller ma lagera) jaane,
			// ani uta receive garne========
			return "redirect:/changePassword/" + usr.getId();
		}

		model.addAttribute("message", "Invalid Email");
		return "ForgotPasswordForm";

	}

	// id receive garne ani usko(User ko) data lagera jaane Change Password Form ma
	@GetMapping("/changePassword/{id}")
	public String getChangePasswordForm(@PathVariable int id, Model model) {

		// yo id baata user find garne

		User user = userService.getUserById(id);
		
//		System.out.println("========== Info ===============");
//		System.out.println(user.getFname());
//		System.out.println(user.getLname());
//		System.out.println(user.getUserName());
//		System.out.println(user.getEmail());
//		System.out.println(user.getPassword());

		
		model.addAttribute("userModel", user);	//("key", value)
		//here, userModel = userObject (j) lekda ni hunxa because User ko object aauxa
		
		return "ChangePasswordForm";
	}
	
	@PostMapping("/changePassword")
	public String postChangePassword(@ModelAttribute User user) {
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userService.updateUser(user);
		
		//return "LoginForm";
		return "redirect:/login";
	}

}
