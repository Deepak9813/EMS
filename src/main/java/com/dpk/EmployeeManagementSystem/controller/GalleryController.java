package com.dpk.EmployeeManagementSystem.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class GalleryController {

	@GetMapping("/gallery")
	public String getGalleryForm(Model model, HttpSession session) {
		
		if(session.getAttribute("validUser") == null) {
			
			return "LoginForm";
		}
		
		String[] imageNames = new File("src/main/resources/static/images").list();
		model.addAttribute("imgNameList", imageNames);		//("key", value)
		
		return "GalleryForm";
	}
	
}
