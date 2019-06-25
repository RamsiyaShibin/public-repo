package com.cdt.services;



import static com.cdt.constants.TestHelper.PRODUCT_LIST;

import static com.cdt.constants.TestHelper.purchaseBill;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdt.Interfaces.IPurchaseService;
import com.cdt.model.PurchaseBillNetAmount;
import com.tripworld.domain.partner.PartnerResult;



@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceTest {
	@Autowired
	private IPurchaseService partnerService;
	
	@Test
	public void getPurchaseTest() throws Exception {
		assertEquals((double) 100,partnerService.getDiscount(PRODUCT_LIST, 30),0.001);
		assertEquals((double) 95,partnerService.getDiscountOfEveryHundred(100),0.001);
		assertEquals((double) 95,partnerService.calculateNetAmountBill(purchaseBill).getNetAmount(),0.001);
		
	}
}
