package com.nagarro.employeemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nagarro.employeemanagement.models.Employee;

@Service
public interface EmployeeService {

	public Employee addEmployee(Employee emp);

	public List<Employee> getEmployees();

	public Employee updateEmployee(Employee employee, long id);

	public Employee findByEmployeeCode(long id);

	public void deleteEmployee(long id);

}
