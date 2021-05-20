package com.testapp.companymanagement.services.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;

import com.testapp.companymanagement.DTOs.EmployeeDTO;
import com.testapp.companymanagement.entities.Employee;
import com.testapp.companymanagement.usefull.ContractType;

public interface IEmployeeService {

	public void create(EmployeeDTO employeeDto);
	
	public JSONObject update(EmployeeDTO employeeDto) throws JSONException;
	
	public Optional<Employee> getById(Integer id);
	
	public List<Employee> getEmployees(Integer companyId, LocalDate hiringDate, ContractType contractType, Float salary) throws Exception ;


}
