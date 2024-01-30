package com.dpk.EmployeeManagementSystem.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dpk.EmployeeManagementSystem.utils.MailUtil;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {

	@Autowired
	private MailUtil mailUtil;

	@GetMapping("/contact")
	public String getContactForm(HttpSession session) {
		
		if(session.getAttribute("validUser") == null) {
			
			return "LoginForm";
		}

		return "ContactForm";
	}

	@PostMapping("/contact")
	public String postContact(@RequestParam String toEmail, @RequestParam String subject, @RequestParam String message, @RequestParam MultipartFile attachFile, Model model) throws MessagingException, IOException {
		
		//================ simple mail ================
		//mailUtil.sendEmail(toEmail, subject, message);
		
		//=============== mail with attach file(pdf, doc, image, etc) ==============
		//mailUtil.sendEmailWithAttachment(toEmail, subject, message, attachFile);
		//return "ContactForm";
		//return "redirect:/contact";
		
		
		//========= BackEnd Validation =================
		
		if((toEmail != null) && (!toEmail.equals("")) && (!toEmail.isEmpty())) {
		
			//toEmail ko check nagarda ni hunxa direct toEmail ko field ma required rakhdine
			
			if(((subject != null) && (!subject.equals("")) && (!subject.isEmpty())) || ((message != null) && (!message.equals("")) && (!message.isEmpty())) || ((attachFile != null) && (!attachFile.equals("")) && (!attachFile.isEmpty()))){
				
				
				mailUtil.sendEmailWithAttachment(toEmail, subject, message, attachFile);
				
				model.addAttribute("msgSuccess", "Send Success....");	//("key", value)
				return "ContactForm";
				//return "redirect:/contact";	
			}
			
			//else nalekhera direct lekha ni hunxa
			else {
				
				model.addAttribute("msgError", "Please fill(include) at least one field either subject or message or attachFile");	//("key", value)
				return "ContactForm";
			
			}	
			
	}
		
		model.addAttribute("msgError", "Please enter the Recipient(Receiver) Email"); //("key", value)
		return "ContactForm";
		
		
	}

}
