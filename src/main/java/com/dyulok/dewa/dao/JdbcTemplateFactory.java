package com.dyulok.dewa.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JdbcTemplateFactory {

	private static JdbcTemplate jdbcTemplate;
	
	
	
	public static void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		JdbcTemplateFactory.jdbcTemplate = jdbcTemplate;
	}



	public static JdbcTemplate getJdbcTemplate(){
		
		if(jdbcTemplate==null)
		{
			MysqlDataSource dataSource=new MysqlDataSource();
			dataSource.setDatabaseName("movie_ticketing_system");
			dataSource.setUser("root");
			dataSource.setPassword("boxmarker");
			dataSource.setServerName("localhost");
			jdbcTemplate=new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}
