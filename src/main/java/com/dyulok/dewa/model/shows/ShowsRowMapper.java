package com.dyulok.dewa.model.shows;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ShowsRowMapper implements RowMapper<Show> {

	@Override
	public Show mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Show shows=new Show();
		shows.setShowId(rs.getInt("SHOW_ID"));
		shows.setHallId(rs.getInt("HALL_ID"));
		shows.setHallName(rs.getString("HALL_NAME"));
		shows.setMovieId(rs.getInt("MOVIE_ID"));
		shows.setMovieName(rs.getString("MOVIE_NAME"));
		shows.setStartTime(rs.getTime("START_TIME"));
		shows.setEndTime(rs.getTime("END_TIME"));
		shows.setFromDate(rs.getDate("FROM_DATE"));
		shows.setToDate(rs.getDate("TO_DATE"));
		shows.setPremiumPrice(rs.getFloat("PREMIUM_SEAT_PRICE"));
		shows.setGoldPrice(rs.getFloat("GOLD_SEAT_PRICE"));
		shows.setSilverPrice(rs.getFloat("SILVER_SEAT_PRICE"));
		return shows;
	}

}
