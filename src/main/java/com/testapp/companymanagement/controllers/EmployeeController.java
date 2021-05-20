package com.testapp.companymanagement.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.testapp.companymanagement.DTOs.EmployeeDTO;
import com.testapp.companymanagement.entities.Employee;
import com.testapp.companymanagement.services.EmployeeService;
import com.testapp.companymanagement.usefull.ContractType;
import com.testapp.companymanagement.usefull.Response;
import com.testapp.companymanagement.usefull.ResponseTypes;


@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	private static final Logger logger =LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	EmployeeService es;
	
	@GetMapping
	public ResponseEntity<?> getEmployees(
			@RequestParam(name="company_id",required=false ) Integer companyId,
			@RequestParam(name="hiring_date",required=false) String hiring_date,
			@RequestParam(name = "contract_type",required = false) ContractType contractType,
			@RequestParam(name = "salary",required = false) Float salary) 
	{
		List<Employee> employees = null;
		try {
			employees = es.getEmployees(companyId, hiring_date!= null ? LocalDate.parse(hiring_date): null, contractType, salary);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Issue getting employees : ", e);
		}
		return ResponseEntity.status(HttpStatus.OK).body(Response.of(ResponseTypes.Success, employees));


	}
	
	@GetMapping("/salary")
	public ResponseEntity<?> getSalaries(
			@RequestParam(name="company_id",required=false ) Integer companyId)
	{
		try {
		return ResponseEntity.status(HttpStatus.OK).body(Response.of(ResponseTypes.Success, es.getSalaries(companyId).toString()));
		}catch (Exception e) {
			logger.error("Issue getting salaries : ", e);
			return null;
			
		}		
	}
	

	@PostMapping(value="/create")
	public ResponseEntity<?> create(@Valid @RequestBody EmployeeDTO empDTO)
	{
		
		try {
			es.create(empDTO);
			return ResponseEntity.status(HttpStatus.CREATED).body(Response.of(ResponseTypes.Success, "Successfully created employee"));
		} catch (Exception e) {
			logger.error("Failed to create employee", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.of(ResponseTypes.Error, e.getMessage()));
		}
		
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateCompany(@Valid @RequestBody EmployeeDTO empDTO) {
		try {
			JSONObject response = es.update(empDTO);
			if (response.getString("status").equals("ok"))
				return ResponseEntity.status(HttpStatus.CREATED).body(Response.of(ResponseTypes.Error, "Successfully update employee"));
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.of(ResponseTypes.Error, response.getString("reason")));
				
		} catch (Exception e) {
			logger.error("Failed to update employee", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Response.of(ResponseTypes.Error, e.getMessage()));
		}  

	}
	

}
