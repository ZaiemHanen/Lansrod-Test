package com.testapp.companymanagement.services.interfaces;

import java.util.Optional;

import com.testapp.companymanagement.DTOs.EmployeeDTO;
import com.testapp.companymanagement.entities.Employee;

public interface IEmployeeService {

	public void create(EmployeeDTO employeeDto);
	
	public void update(EmployeeDTO employeeDto);
	
	public Optional<Employee> getById(Integer id);

}
