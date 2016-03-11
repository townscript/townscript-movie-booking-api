package com.dyulok.dewa.model.booking;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookingRowMapper implements RowMapper<Booking> {

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Booking book=new Booking();
		book.setBookingId(rs.getInt("BOOKING_ID"));
		book.setCustomerId(rs.getInt("CUSTOMER_ID"));
		book.setCustomerName(rs.getString("CUSTOMER_NAME"));
		book.setContact(rs.getString("CONTACT"));
		book.setMovieId(rs.getInt("MOVIE_ID"));
		book.setMovieName(rs.getString("MOVIE_NAME"));
		book.setHallId(rs.getInt("HALL_ID"));
		book.setHallName(rs.getString("HALL_NAME"));
		book.setSeatType(rs.getString("SEAT_TYPE"));
		book.setPrice(rs.getFloat("SEAT_PRICE"));
		book.setShowId(rs.getInt("SHOW_ID"));
		book.setBookingDate(rs.getDate("BOOKING_DATE"));
		book.setStartTime(rs.getTime("START_TIME"));
		book.setEndTime(rs.getTime("END_TIME"));
		book.setTotalCost(rs.getFloat("TOTAL_COST"));
		book.setNoOfSeat(rs.getInt("NO_OF_SEATS"));
		book.setVenue(rs.getString("VENUE"));
		return book;
	}

}
