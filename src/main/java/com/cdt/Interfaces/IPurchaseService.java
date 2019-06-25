package com.cdt.Interfaces;

import java.util.List;

import com.cdt.model.Product;
import com.cdt.model.PurchaseBill;
import com.cdt.model.PurchaseBillNetAmount;

public interface IPurchaseService {
	public double getDiscount(List<Product> productList, int discountPercentage);
	public double getDiscountOfEveryHundred(double amt);
	public PurchaseBillNetAmount calculateNetAmountBill(PurchaseBill purchaseBill);
}
