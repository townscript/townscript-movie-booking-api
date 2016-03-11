package com.dyulok.dewa.dao.customer;

import java.util.List;

import com.dyulok.dewa.model.customer.Customer;
public interface CustomerDao {
	
	int addCustomer(Customer customer);
	void deleteCustomer(int customerid);
	void updateCustomer(Customer customer);
	Customer searchCustomerbyid(int customerid);
	List<Customer> searchCustomerbyname(String name);
	List<Customer> searchCustomerbyage(int age);
	List<Customer> searchCustomerbyaddress(String add);
	//void updateCustomer(Customer customer);
}
