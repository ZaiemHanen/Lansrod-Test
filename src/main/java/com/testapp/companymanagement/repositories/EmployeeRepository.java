package com.testapp.companymanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testapp.companymanagement.entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
