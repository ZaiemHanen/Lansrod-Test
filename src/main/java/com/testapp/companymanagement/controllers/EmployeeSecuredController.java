package com.testapp.companymanagement.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testapp.companymanagement.DTOs.EmployeeDTO;
import com.testapp.companymanagement.services.EmployeeService;
import com.testapp.companymanagement.usefull.Response;
import com.testapp.companymanagement.usefull.ResponseTypes;


@RestController
@RequestMapping("secured/employee")
public class EmployeeSecuredController {
private static final Logger logger =LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	EmployeeService es;
	

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

}
