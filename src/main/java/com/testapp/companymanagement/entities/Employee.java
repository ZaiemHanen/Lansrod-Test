package com.testapp.companymanagement.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import com.testapp.companymanagement.usefull.ContractType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@NotNull
	@Column(nullable = false)
	@Size(min=5, max=100)
	private String name;
	@NotNull
	@Column(nullable = false)
	@Size(min=5, max=100)
	private String surname;
	@NotNull
	@Column(nullable = false)
	private Integer social_sec_nb=null;
	@Column(nullable = false)
	@NotNull
	private LocalDate hiring_date;
	private ContractType contract_type;
	@Positive
	private Float salary;
	@ManyToOne(targetEntity = Company.class)
	@JoinColumn (name="company_id", referencedColumnName = "id")
	//@JsonManagedReference
	private Company company;
}
