package com.testapp.companymanagement.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.testapp.companymanagement.DTOs.EmployeeDTO;
import com.testapp.companymanagement.entities.Employee;
import com.testapp.companymanagement.repositories.CompanyRepository;
import com.testapp.companymanagement.repositories.EmployeeRepository;
import com.testapp.companymanagement.services.interfaces.IEmployeeService;



@Service
public class EmployeeService implements IEmployeeService {
	private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);
	@Autowired
	EmployeeRepository er;

	@Autowired
	CompanyRepository cr;

	@Autowired
	private ModelMapper modelMapper;

	private Employee convertToEmployee(EmployeeDTO employeeDto) throws ParseException {
		Employee employee = modelMapper.map(employeeDto, Employee.class);
		employee.setCompany(cr.findById(employeeDto.getCompany_id()).get());
		return employee;
	}

	@Override
	public void create(EmployeeDTO employeeDto) {
		// TODO Auto-generated method stub
		er.save(this.convertToEmployee(employeeDto));

	}

	@Override
	public void update(EmployeeDTO employeeDto) {
		// TODO Auto-generated method stub
		try {
			Employee employee = modelMapper.map(employeeDto, Employee.class);
			if(!this.getById(employee.getId()).isPresent()) throw new Exception(String.format("Cannot find Employee with id {}", employee.getId()));			
			this.create(employeeDto);
		} catch (Exception e) {
			logger.error("Issue updating employee");
			logger.error("Due to : {}",e.getMessage());
		}	}

	@Override
	public Optional<Employee> getById(Integer id) {
		// TODO Auto-generated method stub
		return er.findById(id);
	}

}
