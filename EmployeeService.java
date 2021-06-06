package com.codedecode.demo.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.custom.exception.BusinessException;
import com.codedecode.demo.entity.Employee;
import com.codedecode.demo.repository.EmployeeCrudRepo;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	private EmployeeCrudRepo crudRepo;

	@Override
	public Employee addEmployee(Employee employee) {
		
			if (employee.getName().isEmpty() || employee.getName().length() == 0) {
				throw new BusinessException("601", "Please send proper Name, it's blank ");
			}
			try {
				Employee savedEmployee = crudRepo.save(employee);
				return savedEmployee;
			}
		 catch (IllegalArgumentException e) {
			throw new BusinessException("602", "Given Employee is null" + e.getMessage());
		} catch (Exception e) {
			throw new BusinessException("603","Oops! Something went Wrong in Service Layer while saving employee" + e.getMessage());
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = null;
		try {
			 empList = crudRepo.findAll();
		}catch (Exception e) {
			throw new BusinessException("605","Oops! Something went Wrong in Service Layer while fetching all employee" + e.getMessage());
		}
		if (empList.isEmpty()) {
			throw new BusinessException("604", "Hey List is completely empty , we have nothing to return");
		}
		return empList;
	}

	@Override
	public Employee getEmpById(long id) {
		try {
			return crudRepo.findById(id).get();
		} catch (IllegalArgumentException e) {
			throw new BusinessException("606", "Given Employee Id is null , please send some Id " + e.getMessage());
		} catch (NoSuchElementException e) {
			throw new BusinessException("607", "Given Employee Id does not exist in database " + e.getMessage());
		}
	}

	@Override
	public void deleteEmpById(Long empId) {
		try {
			crudRepo.deleteById(empId);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("608", "Given Employee Id is null , please send some Id " + e.getMessage());
		} catch (NoSuchElementException e) {
			throw new BusinessException("609", "Given Employee Id does not exist in database " + e.getMessage());
		}
		
	}

}
