package com.testapp.companymanagement.services.interfaces;

import java.util.Optional;

import com.testapp.companymanagement.entities.Company;


public interface ICompanyService {

	public void create(Company company);
	
	public void update(Company company);
	
	public Optional<Company> getById(Integer id);
}
