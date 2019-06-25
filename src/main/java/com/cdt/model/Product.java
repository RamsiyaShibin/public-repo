package com.cdt.model;

import com.cdt.enums.ProductType;

public class Product {
	ProductType type;
	double price;

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
