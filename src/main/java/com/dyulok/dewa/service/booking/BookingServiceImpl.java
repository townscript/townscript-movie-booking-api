package com.dyulok.dewa.service.booking;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.booking.BookingDao;
import com.dyulok.dewa.model.booking.Booking;

@Transactional
public class BookingServiceImpl implements BookingService {
	
	private static final Logger logger=Logger.getLogger(BookingServiceImpl.class);
	
	@Autowired
	private BookingDao bookingDao;

	public static Logger getLogger() {
		return logger;
	}

	public BookingDao getBookingDao() {
		return bookingDao;
	}

	public void setBookingDao(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}

	@Override
	public int createBooking(Booking booking) {
		// TODO Auto-generated method stub
		int bookid=bookingDao.createEntryForBooking(booking);
		logger.info("Record Created!!!!");
		return bookid;
	}

	@Override
	public void deleteBooking(int bookid) {
		// TODO Auto-generated method stub
		bookingDao.deleteBookingById(bookid);
	}

	@Override
	public Booking loadDetailsByBookingId(int bookid) {
		// TODO Auto-generated method stub
		return bookingDao.loadDetailsByBookingId(bookid);
	}

	@Override
	public List<Booking> loadDetailsByCustomerId(int custid) {
		// TODO Auto-generated method stub
		return bookingDao.loadDetailsByCustomerId(custid);
	}

	@Override
	public List<Booking> loadDetailsByDate(Date date) {
		// TODO Auto-generated method stub
		return bookingDao.loadDeatilsByBookingDate(date);
	}

}
