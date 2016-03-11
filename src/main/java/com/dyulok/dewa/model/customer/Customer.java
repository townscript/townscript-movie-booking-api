package com.dyulok.dewa.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;

@Entity
@Table(name="CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name="CUSTOMER_ID")
	private int customerId;
	
	@Column(name="AGE")
	private int age;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="CONTACT")
	private String contactNo;
	
	@Column(name="PASSWORD")
	private String password;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}		
}
