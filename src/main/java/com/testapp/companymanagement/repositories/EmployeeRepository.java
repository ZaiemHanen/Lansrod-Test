package com.testapp.companymanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testapp.companymanagement.entities.Company;
import com.testapp.companymanagement.entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {		
	
	@Query("SELECT contract_type, max(salary) , min(salary)  ,avg(salary)  FROM Employee where company=:companyId GROUP BY contract_type")
	public List<List<Object>> getSalaries(@Param("companyId") Company companyId);

}
