package com.dyulok.dewa.service.customer;

import java.util.List;

import com.dyulok.dewa.model.customer.Customer;

public interface CustomerService {

	int saveCustomer(Customer customer);
	Customer getCustomerInfobyid(int custid);
	List<Customer> getCustomerInfoByName(String name);
	List<Customer> getCustomerInfobyage(int age);
	List<Customer> getCustomerInfobyaddress(String add);
	void deleteCustomer(int custid);
	void updateCust_address(int custid,String add);
	void updateCust_contact(int custid,String cont);
	void updateCust_age(int cust,int age);
}
