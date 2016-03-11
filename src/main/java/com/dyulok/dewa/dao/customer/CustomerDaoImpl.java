package com.dyulok.dewa.dao.customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.dyulok.dewa.dao.JdbcTemplateFactory;
import com.dyulok.dewa.dao.customer.CustomerDao;
import com.dyulok.dewa.model.customer.Customer;
import com.dyulok.dewa.model.customer.CustomerRowMapper;

public class CustomerDaoImpl implements CustomerDao {

	
	@Override
	public int addCustomer(final Customer customer) {
		// TODO Auto-generated method stub
		
		final String sql="Insert into Customer(CUSTOMER_ID,CUSTOMER_NAME,ADDRESS,AGE,CONTACT) values ("+customer.getCustomerId()+",'"+customer.getCustomerName()+"','"+customer.getAddress()+"',"+customer.getAge()+","+customer.getContactNo()+")";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyholder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(
				new PreparedStatementCreator(){
					@Override
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						// TODO Auto-generated method stub
						return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					}
				}, keyholder);
		return keyholder.getKey().intValue();
	}

	@Override
	public void deleteCustomer(int customerid) {
		// TODO Auto-generated method stub
		String sql="Delete from customer where CUSTOMER_ID="+customerid+"";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql="Update customer set CUSTOMER_NAME=?,ADDRESS=?,AGE=?,CONTACT=?";
		Object[] params={customer.getCustomerName(),customer.getAddress(),customer.getAge(),customer.getContactNo()};
		int[] types={Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.BIGINT};
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public Customer searchCustomerbyid(int customerid) {
		// TODO Auto-generated method stub
		String sql="Select * from customer where CUSTOMER_ID="+customerid+"";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Customer> idList=jdbcTemplate.query(sql, new CustomerRowMapper());
		if(idList==null||idList.isEmpty()){
			return null;
		}
		return idList.get(0);
	}

	@Override
	public List<Customer> searchCustomerbyname(String name) {
		// TODO Auto-generated method stub
		String sql="Select * from customer where CUSTOMER_NAME='"+name+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Customer> nameList=jdbcTemplate.query(sql, new CustomerRowMapper());
		if(nameList==null||nameList.isEmpty()){
			return null;
		}
		return nameList;
	}

	@Override
	public List<Customer> searchCustomerbyage(int age) {
		// TODO Auto-generated method stub
		String sql="Select * from customer where AGE="+age+"";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Customer> ageList=jdbcTemplate.query(sql, new CustomerRowMapper());
		if(ageList==null||ageList.isEmpty()){
			return null;
		}
		return ageList;
	}

	@Override
	public List<Customer> searchCustomerbyaddress(String add) {
		// TODO Auto-generated method stub
		String sql="Select * from customer where ADDRESS='"+add+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Customer> addList=jdbcTemplate.query(sql, new CustomerRowMapper());
		if(addList==null||addList.isEmpty()){
			return null;
		}
		return addList;
	}

		
}
