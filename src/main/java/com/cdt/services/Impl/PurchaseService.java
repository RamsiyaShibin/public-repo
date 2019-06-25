package com.cdt.services.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cdt.Interfaces.IPurchaseService;
import com.cdt.enums.ProductType;
import com.cdt.model.Product;
import com.cdt.model.PurchaseBill;
import com.cdt.model.PurchaseBillNetAmount;

@Service("partnerServiceImpl")
public class PurchaseService implements IPurchaseService {

	private final static Logger logger = LoggerFactory.getLogger(PurchaseService.class);

	@Autowired
	private CustomerService customerService;

	@Value("${retail.store.discount.percentage.for.employee}")
	int discountForEmployee;

	@Value("${retail.store.discount.percentage.for.affiliate}")
	int discountForAffiliate;

	@Value("${retail.store.discount.percentage.for.customer.more.than.two.years}")
	int discountForCustomerMoreThanTwoYears;

	@Value("${retail.store.discount.amount.on.every.hundred.dollar.bill}")
	int discountForEveryHundredDollarBill;

	@Value("${retail.store.discount.on.grocery.product.flag}")
	boolean isDiscountAppliedOnGroceryProduct;

	@Value("${retail.store.discount.on.non.grocery.product.flag}")
	boolean isDiscountAppliedOnNonGroceryProduct;

	
	/**
	 * Returns an object which contains the amount after discount applied
	 
	 * @author Ramsiya
	 * @param productList is the list of product object arrays
	 * 
	 * @return return the price after discount applied
	 * 
	 */

	@Override
	public double getDiscount(List<Product> productList, int discountPercentage) {
		// TODO Auto-generated method stub
		double priceSum = 0.0;
		for (Product product : productList) {
			logger.info("product price = " + product.getPrice());
			if (product.getType() == ProductType.GROCERY) {
				if (isDiscountAppliedOnGroceryProduct)
					priceSum = priceSum + (product.getPrice() - (product.getPrice() * (discountPercentage / 100.0)));
				else
					priceSum = priceSum + product.getPrice();
			}
			if (product.getType() == ProductType.NONGROCERY) {
				if (isDiscountAppliedOnNonGroceryProduct)
					priceSum = priceSum + (product.getPrice() - (product.getPrice() * (discountPercentage / 100.0)));
				else
					priceSum = priceSum + product.getPrice();
			}

		}
		logger.info("product price = " + priceSum);
		return priceSum;

	}

	@Override
	public double getDiscountOfEveryHundred(double amt) {
		return amt - (((int) (amt / 100.0)) * discountForEveryHundredDollarBill);
	}

	/**
	 * Returns an object which contains the tottal net amount
	 * purchaseBill is a object which contains customer and corresponding document
	 * <p>
	 * This method always calculate the dicount percentage corresponding to the user
	 * types
	 * @author Ramsiya
	 * @param purchaseBill object contains customer and product details
	 * 
	 * @return net price amount
	 * 
	 */

	@Override
	public PurchaseBillNetAmount calculateNetAmountBill(PurchaseBill purchaseBill) {
		double netAmount = 0.0;

		if (customerService.isAnEmployee(purchaseBill.getCustomer())) {
			logger.info("customer is an employee");
			netAmount = netAmount + getDiscount(purchaseBill.getProductList(), discountForEmployee);
			logger.info("net amount = " + netAmount);
		} else if (customerService.isAffiliatedToTheStore(purchaseBill.getCustomer())) {
			logger.info("customer is an affiliate");
			netAmount = netAmount + getDiscount(purchaseBill.getProductList(), discountForAffiliate);
			logger.info("net amount = " + netAmount);
		} else if (customerService.getYearsRelatedWith(purchaseBill.getCustomer()) >= 2) {
			logger.info("customer is more than Two years olse");
			netAmount = netAmount + getDiscount(purchaseBill.getProductList(), discountForCustomerMoreThanTwoYears);
			logger.info("net amount = " + netAmount);
		} else {
			for (Product product : purchaseBill.getProductList()) {
				netAmount = netAmount + product.getPrice();
			}
		}

		netAmount = getDiscountOfEveryHundred(netAmount);
		logger.info("After applying discount on every hundred = " + netAmount);
		PurchaseBillNetAmount purchaseBillNetAmount = new PurchaseBillNetAmount();
		purchaseBillNetAmount.setNetAmount(netAmount);
		return purchaseBillNetAmount;
	}

}
