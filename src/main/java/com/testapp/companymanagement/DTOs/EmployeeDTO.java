package com.testapp.companymanagement.DTOs;

import java.time.LocalDate;

import com.testapp.companymanagement.usefull.ContractType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

	private Integer id;
	private String name;
	private String surname;
	private Integer social_sec_nb;
	private LocalDate hiring_date;
	private ContractType contract_type;
	private Float salary;
	private Integer company_id;
}
