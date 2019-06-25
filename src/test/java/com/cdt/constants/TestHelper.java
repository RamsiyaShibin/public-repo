package com.cdt.constants;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cdt.enums.ProductType;
import com.cdt.model.Customer;
import com.cdt.model.Product;
import com.cdt.model.PurchaseBill;


public class TestHelper {
	
		public static final List<Product> PRODUCT_LIST = new ArrayList<>();
		public static final Product product=new Product();
		public static final Customer customer=new Customer();
		public static final PurchaseBill purchaseBill=new PurchaseBill();
		public static final Date joinedDate = Date.valueOf("2018-01-12");

		static {
			
			product.setPrice(100);
			product.setType(ProductType.GROCERY);
			customer.setCustomerName("Ramsiya");
			customer.setEmployedAT("CDTstore");
			customer.setJoinedDate(joinedDate);
			PRODUCT_LIST.add(product);
			purchaseBill.setCustomer(customer);
			purchaseBill.setProductList(PRODUCT_LIST);

		}
}
