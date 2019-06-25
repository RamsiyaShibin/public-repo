package com.cdt.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cdt.Interfaces.IPurchaseService;
import com.cdt.model.PurchaseBill;
import com.cdt.model.PurchaseBillNetAmount;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripworld.admin.partner.PartnerPageTo;
import com.tripworld.domain.partner.PartnerSearchCriteriTO;


@RunWith(SpringRunner.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	IPurchaseService purchaserService;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void getNetAmountTest() throws Exception {
		PurchaseBillNetAmount purchaseBillNetAmount = new PurchaseBillNetAmount();
		when(purchaserService.calculateNetAmountBill((any(PurchaseBill.class)))).thenReturn(purchaseBillNetAmount);
		mockMvc.perform(put("/purchase/getNetAmount").content(objectMapper.writeValueAsString(purchaseBillNetAmount))
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andExpect(status().isOk()).andReturn();
	}
}
