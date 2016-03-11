package com.dyulok.dewa.model.availability;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AvailabilityRowMapper implements RowMapper<Availability> {

	@Override
	public Availability mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Availability availability= new Availability();
		availability.setAvailabilityId(rs.getInt("AVAILABILITY_ID"));
		availability.setShowId(rs.getInt("SHOW_ID"));
		availability.setMovieId(rs.getInt("MOVIE_ID"));
		availability.setHallId(rs.getInt("HALL_ID"));
		availability.setHallName(rs.getString("HALL_NAME"));
		availability.setVenue(rs.getString("VENUE"));
		availability.setPremiumSeat(rs.getInt("PREMIUM_SEAT"));
		availability.setGoldSeat(rs.getInt("GOLD_SEAT"));
		availability.setSilverSeat(rs.getInt("SILVER_SEAT"));
		availability.setStartTime(rs.getDate("START_TIME"));
		availability.setShowDate(rs.getDate("SHOW_DATE"));
		return availability;
	}

}
