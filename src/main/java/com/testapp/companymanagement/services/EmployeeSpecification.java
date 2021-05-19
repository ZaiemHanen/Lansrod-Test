package com.testapp.companymanagement.services;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.testapp.companymanagement.entities.Employee;
import com.testapp.companymanagement.usefull.ContractType;


public class EmployeeSpecification {
	
	public static Specification<Employee> employeePerCompany(Integer companyId){
		if(companyId == null)
			return null;
		return (root,query,cb) -> cb.equal(root.get("company"), companyId);
	
	}
	
	public static Specification<Employee> employeePerHiringDate(LocalDate date){
		if(date == null)
			return null;
		return (root,query,cb) -> cb.equal(root.get("hiring_date"), date);
	}
	public static Specification<Employee> employeePerContractType(ContractType contractType){
		if(contractType == null)
			return null;
		return (root,query,cb) -> cb.equal(root.get("contrat_type"), contractType);
	}
	public static Specification<Employee> employeePerSalary(Float salary){
		if(salary == null)
			return null;
		return (root,query,cb) -> cb.equal(root.get("salary"), salary);
	}
	
	


}
