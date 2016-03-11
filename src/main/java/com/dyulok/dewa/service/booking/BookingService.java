package com.dyulok.dewa.service.booking;

import java.util.Date;
import java.util.List;

import com.dyulok.dewa.model.booking.Booking;

public interface BookingService {

	int createBooking(Booking booking);
	void deleteBooking(int bookid);
	Booking loadDetailsByBookingId(int bookid);
	List<Booking> loadDetailsByCustomerId(int custid);
	List<Booking> loadDetailsByDate(Date date);
}
