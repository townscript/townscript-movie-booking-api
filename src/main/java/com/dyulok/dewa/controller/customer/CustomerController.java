package com.dyulok.dewa.controller.customer;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.customer.Customer;
import com.dyulok.dewa.service.customer.CustomerService;
//import com.google.gson.Gson;
@RequestMapping("/customer")
@Controller
public class CustomerController {
	
	private CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public CustomerController(){
		//super();
		
		if(customerService==null){
			ApplicationContext context=new ClassPathXmlApplicationContext("/com/dyulok/dewa/mainbeans.xml");
			customerService=(CustomerService)context.getBean("CustomerServiceImpl");
		}
	};
	
	@RequestMapping(value="/savecustomer", method=RequestMethod.POST)
	public @ResponseBody Customer saveCustomer(@RequestParam(value="age") int age,@RequestParam(value="customerName") String customerName,@RequestParam(value="address") String address,@RequestParam(value="contactNo") String contactNo,@RequestParam(value="password") String password){
		
		Customer customer=new Customer();
		customer.setCustomerName(customerName);
		customer.setAddress(address);
		customer.setAge(age);
		customer.setContactNo(contactNo);
		customer.setPassword(password);
		customerService.saveCustomer(customer);
		return customer;
	}
	
	@RequestMapping(value="/deletecustomer", method=RequestMethod.GET)
	public @ResponseBody void deleteCustomer(@RequestParam(value="customerId") int customerId){
		customerService.deleteCustomer(customerId);
	}

	@RequestMapping(value="/updatecustadd", method=RequestMethod.POST)
	public @ResponseBody String updateCust_address(@RequestParam(value="customerId") int customerId,@RequestParam(value="address") String address){
		customerService.updateCust_address(customerId, address);
		return"Address Updated!!!!";
	}
	
	@RequestMapping(value="/updatecustcont", method=RequestMethod.POST)
	public @ResponseBody String updateCust_contact(@RequestParam(value="customerId") int customerId,@RequestParam(value="contactNo") String contactNo){
		customerService.updateCust_contact(customerId, contactNo);
		return"Contact Updated!!!!!";
	}
	
	@RequestMapping(value="/updatecustage", method=RequestMethod.POST)
	public @ResponseBody String updateCust_age(@RequestParam(value="customerId") int customerId,@RequestParam(value="age") int age){
		customerService.updateCust_age(customerId, age);
		return"Age Updated!!!!";
	}
	
	@RequestMapping(value="/infobyid", method=RequestMethod.GET)
	public @ResponseBody Customer getCustomerInfobyid(@RequestParam(value="customerId") int customerId){
		return customerService.getCustomerInfobyid(customerId);
	}
	
	@RequestMapping(value="/infobyname", method=RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomerInfobyname(@RequestParam(value="customerName") String customerName){
		List<Customer> c = customerService.getCustomerInfoByName(customerName);
		return c;
	}
	
	@RequestMapping(value="/infobyage", method=RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomerInfobyage(@RequestParam(value="age") int age){
		return customerService.getCustomerInfobyage(age);
	}
	
	@RequestMapping(value="/infobyadd", method=RequestMethod.GET)
	public @ResponseBody List<Customer> getCustomerInfobyaddress(@RequestParam(value="address") String address){
		return customerService.getCustomerInfobyaddress(address);
	}
}
