package com.cdt.services.Impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cdt.Interfaces.ICustomerService;
import com.cdt.model.Customer;

@Service("customerServiceImpl")
public class CustomerService implements ICustomerService {
	@Value("${retail.store.name}")
	String retailStoreName;

	@Value("${retail.store.affiliates}")
	String[] affiliateStores;

	/**
	  
	 * @author Ramsiya
	 * @param customer is the customer object contains customer details
	 * 
	 * @return return boolean value depends on the user is an employee or not
	 * 
	 */
	@Override
	public boolean isAnEmployee(Customer customer) {
		if (customer.getEmployedAt().compareTo(retailStoreName) == 0) {

			return true;
		}

		return false;
	}

	
	/**
	  
	 * @author Ramsiya
	 * @param customer is the customer object contains customer details
	 * 
	 * @return return boolean value depends on the user has any affliation with the store
	 */
	@Override
	public boolean isAffiliatedToTheStore(Customer customer) {
		boolean affiliate = false;
		for (int i = 0; i < affiliateStores.length; i++) {
			if (customer.getEmployedAt().compareTo(affiliateStores[i]) == 0) {

				affiliate = true;
				break;
			}
		}
		return affiliate;
	}

	
	/**
	  
	 * @author Ramsiya
	 * @param customer is the customer object contains customer details
	 * 
	 * @return return how many years since the user started purchasing on that sore
	 */
	@Override
	public int getYearsRelatedWith(Customer customer) {
		Calendar custCalDate = Calendar.getInstance();
		custCalDate.setTime(customer.getJoinedDate());

		Calendar nowCal = Calendar.getInstance();
		nowCal.setTime(new Date());

		int diff = nowCal.get(Calendar.YEAR) - custCalDate.get(Calendar.YEAR);

		if (custCalDate.get(Calendar.MONTH) > nowCal.get(Calendar.MONTH)
				|| (custCalDate.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH)
						&& custCalDate.get(Calendar.DATE) > nowCal.get(Calendar.DATE))) {
			diff--;

		}

		return diff;
	}

}
