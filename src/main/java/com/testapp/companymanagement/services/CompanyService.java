package com.testapp.companymanagement.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testapp.companymanagement.entities.Company;
import com.testapp.companymanagement.repositories.CompanyRepository;
import com.testapp.companymanagement.services.interfaces.ICompanyService;

@Service
public class CompanyService implements ICompanyService {
	private static final Logger logger =LoggerFactory.getLogger(CompanyService.class);

	@Autowired
	CompanyRepository cr;
	
	@Override
	public void create(Company company) {
		// TODO Auto-generated method stub
		cr.save(company);
		
	}
	
	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub
		try {
			if(!this.getById(company.getId()).isPresent()) throw new Exception(String.format("Cannot find company with id {}", company.getId()));			
			this.create(company);
		} catch (Exception e) {
			logger.error("Issue updating company with id : {}", company.getId());
			logger.error("Due to : {}",e.getMessage());
		}
		
	}
	
	@Override
	public Optional<Company> getById(Integer id) {
		// TODO Auto-generated method stub
		return cr.findById(id);
	}
}
