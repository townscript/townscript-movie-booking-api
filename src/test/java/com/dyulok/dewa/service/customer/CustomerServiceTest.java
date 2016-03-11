package com.dyulok.dewa.service.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Assert;

import com.dyulok.dewa.dao.JdbcTemplateFactory;
import com.dyulok.dewa.model.customer.Customer;
import com.dyulok.dewa.model.customer.CustomerRowMapper;
import com.dyulok.dewa.service.customer.CustomerService;
//import com.dyulok.dewa.service.customer.CustomerServiceImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations={"/com/dyulok/dewa/testbeans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;
	
	
	@Before
	public void createEnvironment(){
		
		String sql="Delete from customer";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment is Set!!!!");
	}
	
	@After
	public void clearEnvironment(){
		
		String sql="Delete from customer";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment Cleared!!!!");
	}
	
	@Test
	public void testSaveCustomer(){
		
		Customer customer=new Customer();
		customer.setCustomerName("Ratnadeep");
		customer.setAddress("Pune");
		customer.setAge(24);
		customer.setContactNo("9552683668");
		
		int custid=customerService.saveCustomer(customer);

		String sql="Select * from customer where CUSTOMER_ID="+custid;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Customer> idList= jdbcTemplate.query(sql, new CustomerRowMapper());
		Assert.assertEquals(custid, idList.get(0).getCustomerId());
		Assert.assertEquals(customer.getCustomerName(), idList.get(0).getCustomerName());
		Assert.assertEquals(customer.getAddress(), idList.get(0).getAddress());
		Assert.assertEquals(customer.getAge(),idList.get(0).getAge());
		Assert.assertEquals(customer.getContactNo(), idList.get(0).getContactNo());
	}
	
	@Test
	public void testDeleteCustomer(){
		
		final String sql="Insert into customer(CUSTOMER_NAME,ADDRESS,AGE,CONTACT) values ('Rahul','Pune',22,'8983564785')";
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(new PreparedStatementCreator(){
			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			}}, keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		
		customerService.deleteCustomer(custid);
		String sqlforCheck="Select * from customer where CUSTOMER_ID="+custid;
		JdbcTemplate jdbcforCheck=JdbcTemplateFactory.getJdbcTemplate();
		List<Customer> idList= jdbcforCheck.query(sqlforCheck, new CustomerRowMapper());
		Assert.assertTrue(idList.isEmpty());
	}
	
	@Test
	public void testUpdateCustAddress(){
		final String sql="Insert into customer(CUSTOMER_NAME,ADDRESS,AGE,CONTACT) values ('Rahul','Pune',22,'8983564785')";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
		}, keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		
		String add="Aurangabad";
		Customer customer=customerService.getCustomerInfobyid(custid);
		customer.setAddress(add);
		customerService.updateCust_address(custid, add);
		String sqlforCheck="Select ADDRESS from customer where CUSTOMER_ID="+custid;
		JdbcTemplate jdbcforCheck=JdbcTemplateFactory.getJdbcTemplate();
		
		String address=jdbcforCheck.queryForObject(sqlforCheck,String.class);
		Assert.assertEquals(customer.getAddress(), address);
	}
	
	@Test
	public void testUpdateContact(){
		final String sql="Insert into customer(CUSTOMER_NAME,ADDRESS,AGE,CONTACT) values ('Rahul','Pune',22,'8983564785')";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		},keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		
		String cont="9552683668";
		Customer customer=customerService.getCustomerInfobyid(custid);
		customer.setContactNo(cont);
		customerService.updateCust_contact(custid, cont);
		String sqlforCheck="Select CONTACT from customer where CUSTOMER_ID="+custid;
		JdbcTemplate jdbcforCheck=JdbcTemplateFactory.getJdbcTemplate();
		
		String contact=jdbcforCheck.queryForObject(sqlforCheck,String.class);
		Assert.assertEquals(customer.getContactNo(), contact);
	}
	
	@Test
	public void testUpdateAge(){
		final String sql="Insert into customer(CUSTOMER_NAME,ADDRESS,AGE,CONTACT) values ('Rahul','Pune',22,'8983564785')";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		int newAge=23;
		Customer customer=customerService.getCustomerInfobyid(custid);
		customer.setAge(newAge);
		customerService.updateCust_age(custid, newAge);
		String sqlforCheck="Select AGE from customer where CUSTOMER_ID="+custid;
		JdbcTemplate jdbcforCheck=JdbcTemplateFactory.getJdbcTemplate();
		
		int tage=jdbcforCheck.queryForInt(sqlforCheck);
		Assert.assertEquals(customer.getAge(), tage);
	}
	
	@Test
	public void testGetInfoById(){
		final String sql="INSERT INTO CUSTOMER(CUSTOMER_NAME,ADDRESS,AGE,CONTACT) VALUES ('Rahul','Pune',22,'8983564785')";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		
		Customer customer=customerService.getCustomerInfobyid(custid);
		Assert.assertEquals(custid, customer.getCustomerId());
		Assert.assertEquals("Rahul", customer.getCustomerName());
		Assert.assertEquals("Pune", customer.getAddress());
		Assert.assertEquals(22, customer.getAge());
		Assert.assertEquals("8983564785", customer.getContactNo());
	}
	
	@Test
	public void testGetInfoByName(){
		final String sql="Insert into customer(CUSTOMER_NAME,ADDRESS,AGE,CONTACT) values ('Rahul','Pune',22,'8983564785')";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		
		String sqlForName="SELECT CUSTOMER_NAME FROM CUSTOMER WHERE CUSTOMER_ID="+custid;
		
		JdbcTemplate jdbcName=JdbcTemplateFactory.getJdbcTemplate();
		String name=jdbcName.queryForObject(sqlForName, String.class);
				
		List<Customer> customer=customerService.getCustomerInfoByName(name);
		Assert.assertEquals(custid, customer.get(0).getCustomerId());
		Assert.assertEquals(name, customer.get(0).getCustomerName());
		Assert.assertEquals("Pune", customer.get(0).getAddress());
		Assert.assertEquals(22,customer.get(0).getAge());
		Assert.assertEquals("8983564785", customer.get(0).getContactNo());
	}
	
	@Test
	public void testGetInfoByAge(){
		final String sql="Insert into customer(CUSTOMER_NAME,ADDRESS,AGE,CONTACT)values('Rahul','Pune',22,'8983564785')";
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		
		String sqlForAge="SELECT AGE FROM CUSTOMER WHERE CUSTOMER_ID="+custid;
		
		JdbcTemplate jdbcAge=JdbcTemplateFactory.getJdbcTemplate();
		int age=jdbcAge.queryForInt(sqlForAge);
		
		List<Customer> customer=customerService.getCustomerInfobyage(age);
		
		Assert.assertEquals(custid, customer.get(0).getCustomerId());
		Assert.assertEquals("Rahul", customer.get(0).getCustomerName());
		Assert.assertEquals("Pune", customer.get(0).getAddress());
		Assert.assertEquals(age,customer.get(0).getAge());
		Assert.assertEquals("8983564785", customer.get(0).getContactNo());
	}
	
	@Test
	public void testGetInfoByAddress(){
		final String sql="Insert into customer(CUSTOMER_NAME,ADDRESS,AGE,CONTACT) values ('Rahul','Pune',22,'8983564785')";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int custid=keyHolder.getKey().intValue();
		
		String sqlForAddress="SELECT ADDRESS FROM CUSTOMER WHERE CUSTOMER_ID="+custid;
		JdbcTemplate jdbcAddress=JdbcTemplateFactory.getJdbcTemplate();
		
		String add=jdbcAddress.queryForObject(sqlForAddress, String.class);
		List<Customer> customer=customerService.getCustomerInfobyaddress(add);
		
		Assert.assertEquals(custid,customer.get(0).getCustomerId());
		Assert.assertEquals("Rahul",customer.get(0).getCustomerName());
		Assert.assertEquals(add,customer.get(0).getAddress());
		Assert.assertEquals(22,customer.get(0).getAge());
		Assert.assertEquals("8983564785",customer.get(0).getContactNo());
	}
}
