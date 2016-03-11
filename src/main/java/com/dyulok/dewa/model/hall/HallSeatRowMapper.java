package com.dyulok.dewa.model.hall;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class HallSeatRowMapper implements RowMapper<Hall> {

	@Override
	public Hall mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Hall allhall=new Hall();
		allhall.setPremiumSeat(rs.getInt("PREMIUM_SEAT"));
		allhall.setGoldSeat(rs.getInt("GOLD_SEAT"));
		allhall.setSilverSeat(rs.getInt("SILVER_SEAT"));
		return allhall;
	}

}
