package com.dyulok.dewa.service.booking;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dyulok.dewa.dao.JdbcTemplateFactory;
import com.dyulok.dewa.model.booking.Booking;
import com.dyulok.dewa.model.booking.BookingRowMapper;
import com.dyulok.dewa.model.customer.Customer;
import com.dyulok.dewa.model.hall.Hall;
import com.dyulok.dewa.model.movie.Movie;
import com.dyulok.dewa.model.shows.Show;
import com.dyulok.dewa.service.customer.CustomerService;
import com.dyulok.dewa.service.hall.HallService;
import com.dyulok.dewa.service.movie.MovieService;
import com.dyulok.dewa.service.shows.ShowsService;

@ContextConfiguration(locations="/com/dyulok/dewa/testbeans.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class BookingServiceTest {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	HallService hallService;
	
	@Autowired
	ShowsService showService;
	
	Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("IST"));
	public SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
	public SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
	
	@Before
	public void createEnvironment(){
		
		final String query="DELETE FROM BOOKING";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(query);
		
		System.out.println("Environment is Created!!!!!");
	}
	
	@After
	public void clearEnvironment(){
		
		final String query="DELETE FROM BOOKING";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(query);
		
		System.out.println("Environment is Cleared!!!!!");
	}
	
	@Test
	public void testCreateBooking(){
		
		Customer customer=new Customer();
		customer.setCustomerName("Ratnadeep");
		customer.setAddress("Pune");
		customer.setAge(24);
		customer.setContactNo("8983564785");
		
		int custid=customerService.saveCustomer(customer);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("UA");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Show show=new Show();
		show.setHallId(hallid);
		show.setHallName("Cinepolis");
		show.setMovieId(movieid);
		show.setMovieName("Deadpool");
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date startTime=cal.getTime();
		show.setStartTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date endTime=cal.getTime();
		show.setEndTime(endTime);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date fromDate=cal.getTime();
		show.setFromDate(fromDate);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		Date toDate=cal.getTime();
		show.setToDate(toDate);
		show.setPremiumPrice(180f);
		show.setGoldPrice(140f);
		show.setSilverPrice(100f);
		
		int showid=showService.saveShow(show);
		
		Booking book=new Booking();
		book.setCustomerId(custid);
		book.setCustomerName("Ratnadeep");
		book.setContact("8983564785");
		book.setMovieId(movieid);
		book.setMovieName("Deadpool");
		book.setHallId(hallid);
		book.setHallName("Cinepolis");
		book.setSeatType("Premium");
		book.setPrice(180f);
		book.setShowId(showid);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bookDate=cal.getTime();
		book.setBookingDate(bookDate);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date stime=cal.getTime();
		book.setStartTime(stime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date etime=cal.getTime();
		book.setEndTime(etime);
		book.setTotalCost(900f);
		book.setNoOfSeat(5);
		book.setVenue("Pune");
		
		int bookid=bookingService.createBooking(book);
		
		Booking booking=bookingService.loadDetailsByBookingId(bookid);
		
		Assert.assertEquals(bookid, booking.getBookingId());
		
		Assert.assertEquals(custid, booking.getCustomerId());
		
		Assert.assertEquals("Ratnadeep", booking.getCustomerName());
		
		Assert.assertEquals("8983564785", booking.getContact());
		
		Assert.assertEquals(movieid, booking.getMovieId());
		
		Assert.assertEquals("Deadpool", booking.getMovieName());
		
		Assert.assertEquals(hallid, booking.getHallId());
		
		Assert.assertEquals("Cinepolis", booking.getHallName());
		
		Assert.assertEquals("Premium", booking.getSeatType());
		
		Assert.assertEquals(180.0, booking.getPrice(), 0.0);
		
		Assert.assertEquals(showid, booking.getShowId());
		
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bDate=cal.getTime();
		String expectedDate=sdfDate.format(bDate);
		String actualDate=sdfDate.format(booking.getBookingDate());
		Assert.assertEquals(expectedDate, actualDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date sTime=cal.getTime();
		String expectedSTime=sdfTime.format(sTime);
		String actualSTime=sdfTime.format(booking.getStartTime());
		Assert.assertEquals(expectedSTime, actualSTime);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date eTime=cal.getTime();
		String expectedETime=sdfTime.format(eTime);
		String actualETime=sdfTime.format(booking.getEndTime());
		Assert.assertEquals(expectedETime, actualETime);
		
		Assert.assertEquals(900.0, booking.getTotalCost(), 0.0);
		
		Assert.assertEquals(5, booking.getNoOfSeat());
		
		Assert.assertEquals("Pune",booking.getVenue());
		
	}
	
	@Test
	public void testDeleteBooking(){
		
		Customer customer=new Customer();
		customer.setCustomerName("Ratnadeep");
		customer.setAddress("Pune");
		customer.setAge(24);
		customer.setContactNo("8983564785");
		
		int custid=customerService.saveCustomer(customer);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("UA");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Show show=new Show();
		show.setHallId(hallid);
		show.setHallName("Cinepolis");
		show.setMovieId(movieid);
		show.setMovieName("Deadpool");
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date startTime=cal.getTime();
		show.setStartTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date endTime=cal.getTime();
		show.setEndTime(endTime);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date fromDate=cal.getTime();
		show.setFromDate(fromDate);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		Date toDate=cal.getTime();
		show.setToDate(toDate);
		show.setPremiumPrice(180f);
		show.setGoldPrice(140f);
		show.setSilverPrice(100f);
		
		int showid=showService.saveShow(show);
		
		Booking book=new Booking();
		book.setCustomerId(custid);
		book.setCustomerName("Ratnadeep");
		book.setContact("8983564785");
		book.setMovieId(movieid);
		book.setMovieName("Deadpool");
		book.setHallId(hallid);
		book.setHallName("Cinepolis");
		book.setSeatType("Premium");
		book.setPrice(180f);
		book.setShowId(showid);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bookDate=cal.getTime();
		book.setBookingDate(bookDate);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date stime=cal.getTime();
		book.setStartTime(stime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date etime=cal.getTime();
		book.setEndTime(etime);
		book.setTotalCost(900f);
		book.setNoOfSeat(5);
		book.setVenue("Pune");
		
		int bookid=bookingService.createBooking(book);
		
		bookingService.deleteBooking(bookid);
		
		final String checkString="SELECT * FROM BOOKING WHERE BOOKING_ID="+bookid;
		
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		List<Booking> listById=jdbcTemplate.query(checkString, new BookingRowMapper());
		
		Assert.assertTrue(listById.isEmpty());
	}
	
	@Test
	public void testLoadDetailsByBookingId(){
		
		Customer customer=new Customer();
		customer.setCustomerName("Ratnadeep");
		customer.setAddress("Pune");
		customer.setAge(24);
		customer.setContactNo("8983564785");
		
		int custid=customerService.saveCustomer(customer);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("UA");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Show show=new Show();
		show.setHallId(hallid);
		show.setHallName("Cinepolis");
		show.setMovieId(movieid);
		show.setMovieName("Deadpool");
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date startTime=cal.getTime();
		show.setStartTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date endTime=cal.getTime();
		show.setEndTime(endTime);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date fromDate=cal.getTime();
		show.setFromDate(fromDate);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		Date toDate=cal.getTime();
		show.setToDate(toDate);
		show.setPremiumPrice(180f);
		show.setGoldPrice(140f);
		show.setSilverPrice(100f);
		
		int showid=showService.saveShow(show);
		
		Booking book=new Booking();
		book.setCustomerId(custid);
		book.setCustomerName("Ratnadeep");
		book.setContact("8983564785");
		book.setMovieId(movieid);
		book.setMovieName("Deadpool");
		book.setHallId(hallid);
		book.setHallName("Cinepolis");
		book.setSeatType("Premium");
		book.setPrice(180f);
		book.setShowId(showid);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bookDate=cal.getTime();
		book.setBookingDate(bookDate);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date stime=cal.getTime();
		book.setStartTime(stime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date etime=cal.getTime();
		book.setEndTime(etime);
		book.setTotalCost(900f);
		book.setNoOfSeat(5);
		book.setVenue("Pune");
		
		int bookid=bookingService.createBooking(book);
		
		Booking booking=bookingService.loadDetailsByBookingId(bookid);
		
		Assert.assertEquals(bookid, booking.getBookingId());
		
		Assert.assertEquals(custid, booking.getCustomerId());
		
		Assert.assertEquals("Ratnadeep", booking.getCustomerName());
		
		Assert.assertEquals("8983564785", booking.getContact());
		
		Assert.assertEquals(movieid, booking.getMovieId());
		
		Assert.assertEquals("Deadpool", booking.getMovieName());
		
		Assert.assertEquals(hallid, booking.getHallId());
		
		Assert.assertEquals("Cinepolis", booking.getHallName());
		
		Assert.assertEquals("Premium", booking.getSeatType());
		
		Assert.assertEquals(180.0, booking.getPrice(), 0.0);
		
		Assert.assertEquals(showid, booking.getShowId());
		
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bDate=cal.getTime();
		String expectedDate=sdfDate.format(bDate);
		String actualDate=sdfDate.format(booking.getBookingDate());
		Assert.assertEquals(expectedDate, actualDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date sTime=cal.getTime();
		String expectedSTime=sdfTime.format(sTime);
		String actualSTime=sdfTime.format(booking.getStartTime());
		Assert.assertEquals(expectedSTime, actualSTime);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date eTime=cal.getTime();
		String expectedETime=sdfTime.format(eTime);
		String actualETime=sdfTime.format(booking.getEndTime());
		Assert.assertEquals(expectedETime, actualETime);
		
		Assert.assertEquals(900.0, booking.getTotalCost(), 0.0);
		
		Assert.assertEquals(5, booking.getNoOfSeat());
		
		Assert.assertEquals("Pune",booking.getVenue());
	}
	
	@Test
	public void testLoadDetailsByCustomerId(){
		
		Customer customer=new Customer();
		customer.setCustomerName("Ratnadeep");
		customer.setAddress("Pune");
		customer.setAge(24);
		customer.setContactNo("8983564785");
		
		int custid=customerService.saveCustomer(customer);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("UA");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Show show=new Show();
		show.setHallId(hallid);
		show.setHallName("Cinepolis");
		show.setMovieId(movieid);
		show.setMovieName("Deadpool");
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date startTime=cal.getTime();
		show.setStartTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date endTime=cal.getTime();
		show.setEndTime(endTime);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date fromDate=cal.getTime();
		show.setFromDate(fromDate);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		Date toDate=cal.getTime();
		show.setToDate(toDate);
		show.setPremiumPrice(180f);
		show.setGoldPrice(140f);
		show.setSilverPrice(100f);
		
		int showid=showService.saveShow(show);
		
		Booking book=new Booking();
		book.setCustomerId(custid);
		book.setCustomerName("Ratnadeep");
		book.setContact("8983564785");
		book.setMovieId(movieid);
		book.setMovieName("Deadpool");
		book.setHallId(hallid);
		book.setHallName("Cinepolis");
		book.setSeatType("Premium");
		book.setPrice(180f);
		book.setShowId(showid);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bookDate=cal.getTime();
		book.setBookingDate(bookDate);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date stime=cal.getTime();
		book.setStartTime(stime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date etime=cal.getTime();
		book.setEndTime(etime);
		book.setTotalCost(900f);
		book.setNoOfSeat(5);
		book.setVenue("Pune");
		
		int bookid=bookingService.createBooking(book);
		
		final String queryString="SELECT CUSTOMER_ID FROM BOOKING WHERE BOOKING_ID="+bookid;
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		
		int cid=jdbcForCheck.queryForInt(queryString);
		
		List<Booking> booking=bookingService.loadDetailsByCustomerId(cid);
		
		Assert.assertEquals(bookid, booking.get(0).getBookingId());
		
		Assert.assertEquals(cid, booking.get(0).getCustomerId());
		
		Assert.assertEquals("Ratnadeep", booking.get(0).getCustomerName());
		
		Assert.assertEquals("8983564785", booking.get(0).getContact());
		
		Assert.assertEquals(movieid, booking.get(0).getMovieId());
		
		Assert.assertEquals("Deadpool", booking.get(0).getMovieName());
		
		Assert.assertEquals(hallid, booking.get(0).getHallId());
		
		Assert.assertEquals("Cinepolis", booking.get(0).getHallName());
		
		Assert.assertEquals("Premium", booking.get(0).getSeatType());
		
		Assert.assertEquals(180.0, booking.get(0).getPrice(), 0.0);
		
		Assert.assertEquals(showid, booking.get(0).getShowId());
		
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bDate=cal.getTime();
		String expectedDate=sdfDate.format(bDate);
		String actualDate=sdfDate.format(booking.get(0).getBookingDate());
		Assert.assertEquals(expectedDate, actualDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date sTime=cal.getTime();
		String expectedSTime=sdfTime.format(sTime);
		String actualSTime=sdfTime.format(booking.get(0).getStartTime());
		Assert.assertEquals(expectedSTime, actualSTime);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date eTime=cal.getTime();
		String expectedETime=sdfTime.format(eTime);
		String actualETime=sdfTime.format(booking.get(0).getEndTime());
		Assert.assertEquals(expectedETime, actualETime);
		
		Assert.assertEquals(900.0, booking.get(0).getTotalCost(), 0.0);
		
		Assert.assertEquals(5, booking.get(0).getNoOfSeat());
		
		Assert.assertEquals("Pune",booking.get(0).getVenue());
	}
	
	@Test
	public void testLoadDetailsByDate(){
		
		Customer customer=new Customer();
		customer.setCustomerName("Ratnadeep");
		customer.setAddress("Pune");
		customer.setAge(24);
		customer.setContactNo("8983564785");
		
		int custid=customerService.saveCustomer(customer);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("UA");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Show show=new Show();
		show.setHallId(hallid);
		show.setHallName("Cinepolis");
		show.setMovieId(movieid);
		show.setMovieName("Deadpool");
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date startTime=cal.getTime();
		show.setStartTime(startTime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date endTime=cal.getTime();
		show.setEndTime(endTime);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date fromDate=cal.getTime();
		show.setFromDate(fromDate);
		cal.set(Calendar.DAY_OF_MONTH, 28);
		Date toDate=cal.getTime();
		show.setToDate(toDate);
		show.setPremiumPrice(180f);
		show.setGoldPrice(140f);
		show.setSilverPrice(100f);
		
		int showid=showService.saveShow(show);
		
		Booking book=new Booking();
		book.setCustomerId(custid);
		book.setCustomerName("Ratnadeep");
		book.setContact("8983564785");
		book.setMovieId(movieid);
		book.setMovieName("Deadpool");
		book.setHallId(hallid);
		book.setHallName("Cinepolis");
		book.setSeatType("Premium");
		book.setPrice(180f);
		book.setShowId(showid);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bookDate=cal.getTime();
		book.setBookingDate(bookDate);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date stime=cal.getTime();
		book.setStartTime(stime);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date etime=cal.getTime();
		book.setEndTime(etime);
		book.setTotalCost(900f);
		book.setNoOfSeat(5);
		book.setVenue("Pune");
		
		int bookid=bookingService.createBooking(book);
		
		final String queryString="SELECT BOOKING_DATE FROM BOOKING WHERE BOOKING_ID="+bookid;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		Date bDate=jdbcTemplate.queryForObject(queryString, Date.class);
		
		List<Booking> booking=bookingService.loadDetailsByDate(bDate);
		
		Assert.assertEquals(bookid, booking.get(0).getBookingId());
		
		Assert.assertEquals(custid, booking.get(0).getCustomerId());
		
		Assert.assertEquals("Ratnadeep", booking.get(0).getCustomerName());
		
		Assert.assertEquals("8983564785", booking.get(0).getContact());
		
		Assert.assertEquals(movieid, booking.get(0).getMovieId());
		
		Assert.assertEquals("Deadpool", booking.get(0).getMovieName());
		
		Assert.assertEquals(hallid, booking.get(0).getHallId());
		
		Assert.assertEquals("Cinepolis", booking.get(0).getHallName());
		
		Assert.assertEquals("Premium", booking.get(0).getSeatType());
		
		Assert.assertEquals(180.0, booking.get(0).getPrice(), 0.0);
		
		Assert.assertEquals(showid, booking.get(0).getShowId());
		
		/*cal.set(Calendar.DAY_OF_MONTH, 21);
		Date bDate=cal.getTime();*/
		String expectedDate=sdfDate.format(bDate);
		String actualDate=sdfDate.format(booking.get(0).getBookingDate());
		Assert.assertEquals(expectedDate, actualDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date sTime=cal.getTime();
		String expectedSTime=sdfTime.format(sTime);
		String actualSTime=sdfTime.format(booking.get(0).getStartTime());
		Assert.assertEquals(expectedSTime, actualSTime);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date eTime=cal.getTime();
		String expectedETime=sdfTime.format(eTime);
		String actualETime=sdfTime.format(booking.get(0).getEndTime());
		Assert.assertEquals(expectedETime, actualETime);
		
		Assert.assertEquals(900.0, booking.get(0).getTotalCost(), 0.0);
		
		Assert.assertEquals(5, booking.get(0).getNoOfSeat());
		
		Assert.assertEquals("Pune",booking.get(0).getVenue());
	}
}
