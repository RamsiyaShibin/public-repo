package com.cdt.Interfaces;

import com.cdt.model.Customer;

public interface ICustomerService {
	public boolean isAnEmployee(Customer customer);
	public boolean isAffiliatedToTheStore(Customer customer);
	public int getYearsRelatedWith(Customer customer);
}
