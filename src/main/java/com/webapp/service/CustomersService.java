package com.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.model.Customers;
import com.webapp.repository.CustomersRepository;

@Service
public class CustomersService {
	
	@Autowired
	private CustomersRepository customerRepo;

	public List<Customers> getAllCustomers() {
		return customerRepo.findAll();
	}
	
    public void saveCustomer(Customers c)
    {
    	customerRepo.save(c);
    }
    
    public Customers getCustomerById(Long CustomerId)
    {
        boolean testCustomer =customerRepo.existsById(CustomerId);
        if(testCustomer)
        return customerRepo.findById(CustomerId).get();
        else
        	return null;
    
    }
    
    public void deleteCustomerById(Long CustomerId)
    {
    	customerRepo.deleteById(CustomerId);
    }
}
