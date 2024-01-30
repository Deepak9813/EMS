package com.dpk.EmployeeManagementSystem.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dpk.EmployeeManagementSystem.model.Department;
import com.dpk.EmployeeManagementSystem.service.DepartmentService;
import com.dpk.EmployeeManagementSystem.utils.DepartmentExcelView;
import com.dpk.EmployeeManagementSystem.utils.DepartmentPdfView;

import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;

@Log
@Controller
public class DepartmentController {

	// private static final Logger log =
	// LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentService deptService;

	// @RequestMapping(value = "/department", method = RequestMethod.GET)
	@GetMapping("/department")
	public String getDeptForm(HttpSession session) {

		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

		return "DepartmentForm";
	}

	@PostMapping("/department")
	public String postDept(@ModelAttribute Department dept, Model model) {

		// check Department(Department Name ) already exist or not
		Department d = deptService.getDeptByDeptName(dept.getDeptName());
		if (d != null) {

			model.addAttribute("deptAlreadyExist", "Department already exist!..."); // ("key", value)
			return "DepartmentForm";
		}

		log.info("============= Department Added Successfully ========================");

		deptService.addDept(dept);
		// return "DepartmentForm";
		return "redirect:/department"; // GetMapping waala url indicate garxa
	}

	@GetMapping("/deptList")
	public String getAllDept(Model model, HttpSession session) {

		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

//		List<Department> deptList = deptService.getAllDept();
//		model.addAttribute("dList", deptList); // ("key", value)

		model.addAttribute("dList", deptService.getAllDept()); // ("key", value)

		return "DepartmentList";
	}

	@GetMapping("/dept/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {

		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

		log.info("============= Department Deleted Successfully ========================");

		deptService.deleteDept(id);

		return "redirect:/deptList"; // GetMapping waala url indicate garxa
	}

	@GetMapping("/dept/edit")
	public String editDept(@RequestParam int id, Model model, HttpSession session) {

		if (session.getAttribute("validUser") == null) {

			return "LoginForm";
		}

//		Department dept = deptService.getDeptById(id);
//		model.addAttribute("deptModel", dept); // ("key", value)
		// here, deptModel = deptObject (j) lekhda ni huxa because Department ko object
		// aauxa

		model.addAttribute("deptModel", deptService.getDeptById(id)); // ("key", value)

		return "DepartmentEditForm";
	}

	@PostMapping("/dept/update")
	public String updateDept(@ModelAttribute Department dept, Model model) {

		// check Department(Department Name) already exist or not
//		Department d = deptService.getDeptByDeptName(dept.getDeptName());
//		if (d != null) {
//
//			model.addAttribute("deptAlreadyExist", "Department already exist!..."); // ("key", value)
//			return "DepartmentEditForm";
//		}

		log.info("============= Department Updated Successfully ========================");

		deptService.updateDept(dept);

		return "redirect:/deptList";
	}

	@GetMapping("/dept/excel")
	public ModelAndView excel() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", deptService.getAllDept()); // ("key", value)
		mv.setView(new DepartmentExcelView());

		return mv;
	}

	@GetMapping("/dept/pdf")
	public ModelAndView pdf() {

		ModelAndView mv = new ModelAndView();
		mv.addObject("list", deptService.getAllDept()); // ("key", value)
		mv.setView(new DepartmentPdfView());

		return mv;
	}

}
