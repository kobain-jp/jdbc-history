package com.dojo.jdbchistoryrest.controller.web.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dojo.jdbchistoryrest.domain.employee.EmployeeRepository;

@Controller
public class EmployeeIndexController {

	EmployeeRepository repository;

	@Autowired
	public EmployeeIndexController(EmployeeRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/employee")
	public String index(Model model) {

		model.addAttribute("employeeList", repository.findAll());
		return "/employee/index";

	}

	@PostMapping("/employee/search-by-empnm")
	public String findByEmpNm(@RequestParam("searchValue") String searchValue, Model model) {
		model.addAttribute("employeeList", repository.findByEmpNm(searchValue));
		return "/employee/index";
	}

}