package com.dpk.EmployeeManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dpk.EmployeeManagementSystem.model.Department;
import com.dpk.EmployeeManagementSystem.model.Employee;
import com.dpk.EmployeeManagementSystem.service.DepartmentService;
import com.dpk.EmployeeManagementSystem.service.EmployeeService;
import com.dpk.EmployeeManagementSystem.utils.EmployeeExcelView;
import com.dpk.EmployeeManagementSystem.utils.EmployeePdfView;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController {

	@Autowired
	private DepartmentService deptService;

	@Autowired
	private EmployeeService empService;

	@GetMapping("/employee")
	public String getEmpForm(Model model, HttpSession session) {
		
		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

		List<Department> deptList = deptService.getAllDept();
		model.addAttribute("dList", deptList); // ("key", value)

		// model.addAttribute("dList", deptService.getAllDept()); //("key", value)

		return "EmployeeForm";
	}

	@PostMapping("/employee")
	public String postEmp(@ModelAttribute Employee emp, Model model) {

		// check if email already exist or not
		Employee e = empService.getEmpByEmail(emp.getEmail());
		if (e != null) {

			model.addAttribute("message", "Employee(email) already exist!..."); // ("key", value)
			return "EmployeeForm";
		}

		// check if phone already exist or not
		Employee emplo = empService.getEmpByPhone(emp.getPhone());
		if (emplo != null) {

			model.addAttribute("message", "Employee(phone) already exist!...."); // ("key", value)
			return "EmployeeForm";
		}

		empService.addEmp(emp);

		// return "Employeeform";
		return "redirect:/employee";
	}

	@GetMapping("/empList")
	public String getAllEmp(Model model, HttpSession session) {
		
		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

		List<Employee> empList = empService.getAllEmp();
		model.addAttribute("eList", empList); // ("key", value)

		// model.addAttribute("eList", empService.getAllEmp()); //("key", value)

		return "EmployeeList";
	}

	@GetMapping("/emp/delete/{id}")
	public String deleteEmp(@PathVariable Long id, HttpSession session) {
		
		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

		empService.deleteEmp(id);
		return "redirect:/empList";
	}

	@GetMapping("/emp/edit")
	public String updateEmp(@RequestParam Long id, Model model, HttpSession session) {
		
		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

		Employee emp = empService.getEmpById(id);
		model.addAttribute("empModel", emp); // ("key", value)
		// here, empModel = empObject (j) lekda ni hunxa because Employee ko object
		// aauxa

		// model.addAttribute("empModel", empService.getEmpById(id)); //("key", value)

		List<Department> deptList = deptService.getAllDept();
		model.addAttribute("dList", deptList);	//("key", value)
		
		//model.addAttribute("dList", deptService.getAllDept());	//("key", value)

		return "EmployeeEditForm";
	}
	
	@PostMapping("/emp/update")
	public String updateEmp(@ModelAttribute Employee emp) {
		
		empService.updateEmp(emp);
		
		return "redirect:/empList";
	}
	
	@GetMapping("/emp/excel")
	public ModelAndView excel() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", empService.getAllEmp());		//("key", value)
		mv.setView(new EmployeeExcelView());
		
		return mv;
	}
	
	@GetMapping("/emp/pdf")
	public ModelAndView pdf() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", empService.getAllEmp());		//("key", value)
		mv.setView(new EmployeePdfView());
		
		return mv;
	}

}
