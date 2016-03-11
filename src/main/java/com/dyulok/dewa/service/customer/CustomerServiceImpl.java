package com.dyulok.dewa.service.customer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.customer.CustomerDao;
//import com.dyulok.dewa.dao.customer.CustomerDaoHnateImpl;
//import main.java.com.dyulok.dewa.dao.customer.CustomerDaoImpl;
import com.dyulok.dewa.model.customer.Customer;
import com.dyulok.dewa.service.customer.CustomerService;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerDao;
	
	public CustomerDao getCustomerDao(){
		return customerDao;
	}
	public void setCustomerDao(CustomerDao custDao){
		this.customerDao=custDao;
	}
	
	@Override
	public int saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		int userId=customerDao.addCustomer(customer);
		Logger logger= Logger.getLogger(CustomerServiceImpl.class);
		logger.info("Customer Added!!!");
		return userId;
	}

	@Override
	public Customer getCustomerInfobyid(int custid) {
		// TODO Auto-generated method stub
		return customerDao.searchCustomerbyid(custid);
	}

	@Override
	public void deleteCustomer(int custid) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomer(custid);
		Logger logger= Logger.getLogger(CustomerServiceImpl.class);
		logger.info("Customer record with id "+custid+" is deleted!!!!");
	}

	@Override
	public void updateCust_address(int custid, String add) {
		// TODO Auto-generated method stub
		Customer customer=customerDao.searchCustomerbyid(custid);
		customer.setAddress(add);
		customerDao.updateCustomer(customer);
		Logger logger= Logger.getLogger(CustomerServiceImpl.class);
		logger.info("Customer with id "+custid+" address is updated!!!");
	}

	@Override
	public void updateCust_contact(int custid, String cont) {
		// TODO Auto-generated method stub
		Customer customer=customerDao.searchCustomerbyid(custid);
		customer.setContactNo(cont);
		customerDao.updateCustomer(customer);
		Logger logger= Logger.getLogger(CustomerServiceImpl.class);
		logger.info("Customer with id "+custid+" contact number is updated!!!");
	}

	@Override
	public void updateCust_age(int custid, int age) {
		// TODO Auto-generated method stub
		Customer customer=customerDao.searchCustomerbyid(custid);
		customer.setAge(age);
		customerDao.updateCustomer(customer);
		Logger logger= Logger.getLogger(CustomerServiceImpl.class);
		logger.info("Customer with id "+custid+" age is updated!!!");
	}
	
	@Override
	public List<Customer> getCustomerInfoByName(String name) {
		// TODO Auto-generated method stub
		List<Customer> c = customerDao.searchCustomerbyname(name);
		return c;
	}
	@Override
	public List<Customer> getCustomerInfobyage(int age) {
		// TODO Auto-generated method stub
		return customerDao.searchCustomerbyage(age);
	}
	@Override
	public List<Customer> getCustomerInfobyaddress(String add) {
		// TODO Auto-generated method stub
		return customerDao.searchCustomerbyaddress(add);
	}	
}
