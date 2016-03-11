package com.dyulok.dewa.controller.booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.booking.Booking;
import com.dyulok.dewa.service.booking.BookingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping(value="/booking")
@Controller
public class BookingController {

	private BookingService bookingService;

	public BookingService getBookingService() {
		return bookingService;
	}

	public void setBookingService(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	public BookingController(){
		
		if(bookingService==null){
			ApplicationContext context=new ClassPathXmlApplicationContext("/com/dyulok/dewa/mainbeans.xml");
			bookingService=(BookingService)context.getBean("bookingServiceImpl");
		}
	};
	
	@RequestMapping(value="/createbooking", method=RequestMethod.POST)
	public @ResponseBody Booking createBooking(@RequestParam(value="customerId") int customerId,@RequestParam(value="customerName") String customerName,@RequestParam(value="contact") String contact,@RequestParam(value="movieId") int movieId,@RequestParam(value="movieName") String movieName,@RequestParam(value="hallId") int hallId,@RequestParam(value="hallName") String hallName,@RequestParam(value="seatType") String seatType,@RequestParam(value="seatPrice") float seatPrice,@RequestParam(value="showId") int showId,@RequestParam(value="bookingDate") String bookingDate,@RequestParam(value="startTime") String startTime,@RequestParam(value="endTime") String endTime,@RequestParam(value="totalCost") float totalCost,@RequestParam(value="noOfSeat") int noOfSeat,@RequestParam(value="venue") String venue){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Booking booking=new Booking();
		booking.setCustomerId(customerId);
		booking.setCustomerName(customerName);
		booking.setContact(contact);
		booking.setMovieId(movieId);
		booking.setMovieName(movieName);
		booking.setHallId(hallId);
		booking.setHallName(hallName);
		booking.setSeatType(seatType);
		booking.setPrice(seatPrice);
		booking.setShowId(showId);
		Date mbookingDate;
		try {
			mbookingDate = sdfDate.parse(bookingDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		booking.setBookingDate(mbookingDate);
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		booking.setStartTime(mstartTime);
		Date mendTime;
		try {
			mendTime = sdfTime.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		booking.setEndTime(mendTime);
		booking.setTotalCost(totalCost);
		booking.setNoOfSeat(noOfSeat);
		booking.setVenue(venue);
		bookingService.createBooking(booking);
		return booking;
	}
	
	@RequestMapping(value="/deletebooking", method=RequestMethod.GET)
	public @ResponseBody void deleteBooking(@RequestParam(value="bookingId") int bookingId){
		bookingService.deleteBooking(bookingId);
	}
	
	@RequestMapping(value="/bookinginfobyid", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByBookingId(@RequestParam(value="bookingId") int bookingId){
		Booking b=bookingService.loadDetailsByBookingId(bookingId);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		result.put("data", gson.toJson(b));
		return result;
	}
	
	@RequestMapping(value="/bookinginfobycustid", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByCustomerId(@RequestParam(value="customerId") int customerId){
		List<Booking> b=bookingService.loadDetailsByCustomerId(customerId);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		result.put("data", gson.toJson(b));
		return result;
	}
	
	@RequestMapping(value="/bookinginfobydate", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByDate(@RequestParam(value="bookingDate") String bookingDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date bdate;
		try {
			bdate = sdf.parse(bookingDate);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		List<Booking> b=bookingService.loadDetailsByDate(bdate);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		result.put("data", gson.toJson(b));
		return result;
	}
}
