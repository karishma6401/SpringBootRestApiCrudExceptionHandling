package com.codedecode.demo.service;

import java.util.List;

import com.codedecode.demo.entity.Employee;

public interface EmployeeServiceInterface {

	public Employee addEmployee(Employee employee);
	
	public List<Employee> getAllEmployees();

	public Employee getEmpById(long id);

	public void deleteEmpById(Long empId);
}
