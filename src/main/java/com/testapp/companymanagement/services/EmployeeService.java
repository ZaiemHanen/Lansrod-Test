package com.testapp.companymanagement.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.testapp.companymanagement.DTOs.EmployeeDTO;
import com.testapp.companymanagement.entities.Employee;
import com.testapp.companymanagement.repositories.CompanyRepository;
import com.testapp.companymanagement.repositories.EmployeeRepository;
import com.testapp.companymanagement.services.interfaces.IEmployeeService;
import com.testapp.companymanagement.usefull.ContractType;



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

	@Override
	public List<Employee> getEmployees(Integer companyId, LocalDate hiringDate, ContractType contractType,
			Float salary) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<Employee> employees = er
					.findAll(Specification.where(EmployeeSpecification.employeePerCompany(companyId))
							.and(EmployeeSpecification.employeePerHiringDate(hiringDate))
							.and(EmployeeSpecification.employeePerContractType(contractType))
							.and(EmployeeSpecification.employeePerSalary(salary)));
			return employees;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new Exception(e);
		}
	}
	
	public JSONObject getSalaries(Integer companyId) {
		try {

			List<List<Object>> salaryData = er
					.getSalaries(cr.findById(companyId).get());
			return this.parseResultToMap(salaryData);

		} catch (Exception e) {
			logger.error(e.toString());
			// throw new Exception(e);
		}
		return null;
	}

	private JSONObject parseResultToMap(List<List<Object>> slaryData) throws JSONException {
		JSONObject parsedResult = new JSONObject();
		for (List<Object> list : slaryData) {
			JSONObject element = new JSONObject();
			element.put("Maxsalary", list.get(1));
			element.put("MinSalary", list.get(2));
			element.put("AverageSalary", list.get(3));
			parsedResult.put(list.get(0).toString(), element.toString());
		}
		return parsedResult;
	}


}
