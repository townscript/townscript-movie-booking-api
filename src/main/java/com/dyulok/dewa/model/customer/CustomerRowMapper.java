package com.dyulok.dewa.model.customer;

import java.sql.*;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Customer allcust=new Customer();
		allcust.setCustomerId(rs.getInt("CUSTOMER_ID"));
		allcust.setCustomerName(rs.getString("CUSTOMER_NAME"));
		allcust.setAddress(rs.getString("ADDRESS"));
		allcust.setAge(rs.getInt("AGE"));
		allcust.setContactNo(rs.getString("CONTACT"));
		return allcust;
	}	
}
