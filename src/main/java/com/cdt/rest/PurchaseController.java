package com.cdt.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.cdt.Interfaces.IPurchaseService;
import com.cdt.model.PurchaseBill;
import com.cdt.model.PurchaseBillNetAmount;

@RestController("PurchaseController")
@RequestMapping("/purchase")
public class PurchaseController {
	@Autowired
	private IPurchaseService purchaseService;
   
	@PutMapping("/getNetAmount")
	public PurchaseBillNetAmount getNetAmount(@RequestBody PurchaseBill purchaseBill) {
		PurchaseBillNetAmount netAmount = purchaseService.calculateNetAmountBill(purchaseBill);
		return netAmount;
	}
}
