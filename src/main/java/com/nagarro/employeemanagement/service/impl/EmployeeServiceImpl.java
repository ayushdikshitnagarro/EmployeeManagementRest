package com.nagarro.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.employeemanagement.models.Employee;
import com.nagarro.employeemanagement.repository.EmployeeRepository;
import com.nagarro.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee addEmployee(Employee emp) {
		return this.employeeRepository.save(emp);
	}

	public List<Employee> getEmployees() {
		List<Employee> list = (List<Employee>) this.employeeRepository.findAll();
		return list;

	}

	public Employee updateEmployee(Employee employee, long id) {
		return this.employeeRepository.save(employee);
	}

	public Employee findByEmployeeCode(long id) {
		return this.employeeRepository.findByEmployeeCode(id);
	}

	public void deleteEmployee(long id) {
		this.employeeRepository.deleteById(id);
	}

}
