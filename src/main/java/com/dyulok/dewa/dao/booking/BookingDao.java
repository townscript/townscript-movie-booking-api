package com.dyulok.dewa.dao.booking;

import java.util.Date;
import java.util.List;

import com.dyulok.dewa.model.booking.Booking;

public interface BookingDao {

	int createEntryForBooking(Booking booking);
	void deleteBookingById(int bookid);
	Booking loadDetailsByBookingId(int bookid);
	List<Booking> loadDetailsByCustomerId(int custid);
	List<Booking> loadDeatilsByBookingDate(Date date);
}
