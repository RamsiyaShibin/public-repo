package com.cdt.services;

import static com.cdt.constants.TestHelper.PRODUCT_LIST;
import static com.cdt.constants.TestHelper.purchaseBill;
import static com.cdt.constants.TestHelper.customer;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdt.Interfaces.ICustomerService;
import com.cdt.Interfaces.IPurchaseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	@Autowired
	private ICustomerService customerService;
	
	@Test
	public void getPurchaseTest() throws Exception {
		assertEquals(true,customerService.isAffiliatedToTheStore(customer));
		assertEquals(true,customerService.isAnEmployee(customer));
		assertEquals(1,customerService.getYearsRelatedWith(customer));
		
	}
}
