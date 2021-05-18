package com.testapp.companymanagement.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@NotNull
	@Column(nullable = false)
	@Size(min=5, max=100)
	private String social_reason;
	@NotNull
	@Column(nullable = false)
	private String siren;
	@NotNull
	@Column(nullable = false)
	private String siret;
	@Size(min=5, max=100)
	private String address;
	@OneToMany(targetEntity = Employee.class, mappedBy = "company", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Employee> employees = new ArrayList<>();
	
	
}