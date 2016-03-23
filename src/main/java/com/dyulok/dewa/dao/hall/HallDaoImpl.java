package com.dyulok.dewa.dao.hall;

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
import com.dyulok.dewa.model.hall.Hall;
import com.dyulok.dewa.model.hall.HallRowMapper;
import com.dyulok.dewa.model.hall.HallSeatRowMapper;

public class HallDaoImpl implements HallDao {

	@Override
	public int addHall(Hall hall) {
		// TODO Auto-generated method stub
		final String sql="INSERT INTO HALL(HALL_ID,HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT) VALUES ("+hall.getHallId()+",'"+hall.getHallName()+"','"+hall.getVenue()+"',"+hall.getPremiumSeat()+","+hall.getGoldSeat()+","+hall.getSilverSeat()+")";
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
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public void deleteHall(int hallId) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM HALL WHERE HALL_ID="+hallId;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
	}

	@Override
	public void updateHall(Hall hall) {
		// TODO Auto-generated method stub
		String sql="UPDATE HALL SET HALL_ID=?,HALL_NAME=?,VENUE=?,PREMIUM_SEAT=?,GOLD_SEAT=?,SILVER_SEAT=?";
		Object[] params={hall.getHallId(),hall.getHallName(),hall.getVenue(),hall.getPremiumSeat(),hall.getGoldSeat(),hall.getSilverSeat()};
		int[] types={Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public Hall loadHallbyid(int hallId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM HALL WHERE HALL_ID="+hallId;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Hall> idList=jdbcTemplate.query(sql, new HallRowMapper());
		if(idList==null||idList.isEmpty()){
			return null;
		}
		return idList.get(0);
	}

	@Override
	public List<Hall> loadHallbyName(String hallName) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM HALL WHERE HALL_NAME='"+hallName+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Hall> nameList=jdbcTemplate.query(sql, new HallRowMapper());
		if(nameList==null||nameList.isEmpty()){
			return null;
		}
		return nameList;
	}

	@Override
	public List<Hall> loadHallbyVenue(String hallVenue) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM HALL WHERE VENUE='"+hallVenue+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Hall> venueList=jdbcTemplate.query(sql, new HallRowMapper());
		if(venueList==null||venueList.isEmpty()){
			return null;
		}
		return venueList;
	}

	@Override
	public Hall getSeatDetailsById(int hallId) {
		// TODO Auto-generated method stub
		String sql="SELECT PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT FROM HALL WHERE HALL_ID="+hallId;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Hall> seatList=jdbcTemplate.query(sql, new HallSeatRowMapper());
		if(seatList==null||seatList.isEmpty()){
			return null;
		}
		return seatList.get(0);
	}

	@Override
	public List<Hall> getSeatDetailsByName(String hallName) {
		// TODO Auto-generated method stub
		String sql="SELECT PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT FROM HALL WHERE HALL_NAME='"+hallName+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Hall> seatList=jdbcTemplate.query(sql, new HallSeatRowMapper());
		if(seatList==null||seatList.isEmpty()){
			return null;
		}
		return seatList;
	}
}