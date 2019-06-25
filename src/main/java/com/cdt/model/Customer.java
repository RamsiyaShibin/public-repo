package com.cdt.model;

import java.util.Date;

public class Customer {
	String customerName;
	String employedAt;
	Date joinedDate;

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmployedAt() {
		return employedAt;
	}

	public void setEmployedAT(String employedAt) {
		this.employedAt = employedAt;
	}

}
