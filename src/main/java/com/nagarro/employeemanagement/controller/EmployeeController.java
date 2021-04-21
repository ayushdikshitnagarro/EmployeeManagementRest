package com.nagarro.employeemanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.employeemanagement.constants.Constants;
import com.nagarro.employeemanagement.models.Employee;
import com.nagarro.employeemanagement.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@RequestMapping("/")
	@ResponseBody
	public String Login() {
		return Constants.WELCOME;
	}

	@PostMapping(Constants.ADD_URL)
	public ResponseEntity<Employee> addEmployee(@RequestBody String json) throws Exception {
		ObjectMapper obj = new ObjectMapper();
		Employee employee = obj.readValue(json, Employee.class);

		if (empService.findByEmployeeCode(employee.getEmployeeCode()) == null) {
			this.empService.addEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

	}

	@GetMapping(Constants.GET_ALL_EMPLOYEES_URL)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> list = this.empService.getEmployees();
		if (list.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}

	@PostMapping(Constants.UPDATE_EMPLOYEE)
	public ResponseEntity<Employee> updateEmployee(@RequestBody String json, @PathVariable("id") long id)
			throws Exception {
		ObjectMapper obj = new ObjectMapper();
		Employee employee = obj.readValue(json, Employee.class);
		this.empService.addEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping(Constants.GET_EMPLOYEE_BY_ID)
	public Employee getEmployeeById(@PathVariable("id") long id) {
		Employee employee = this.empService.findByEmployeeCode(id);
		return employee;
	}

}
