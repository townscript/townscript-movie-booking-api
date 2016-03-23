package com.dyulok.dewa.service.availability;

/*import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;*/
import java.text.DateFormat;
import java.text.ParseException;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dyulok.dewa.dao.JdbcTemplateFactory;
import com.dyulok.dewa.model.availability.Availability;
import com.dyulok.dewa.model.availability.AvailabilityRowMapper;
import com.dyulok.dewa.model.hall.Hall;
import com.dyulok.dewa.model.movie.Movie;
import com.dyulok.dewa.model.shows.Show;
import com.dyulok.dewa.service.hall.HallService;
import com.dyulok.dewa.service.movie.MovieService;
import com.dyulok.dewa.service.shows.ShowsService;

@ContextConfiguration(locations="/com/dyulok/dewa/testbeans.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class AvailabilityServiceTest {

	@Autowired
	public AvailabilityService availabilityService;
	
	@Autowired
	public MovieService movieService;
	
	@Autowired
	public HallService hallService;
	
	@Autowired
	public ShowsService showsService;
	
	public SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
	public SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
	public Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("IST"));
	KeyHolder keyHolder=new GeneratedKeyHolder();
	
	@Before
	public void createEnvironment(){
		String sql="DELETE FROM AVAILABILITY";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment Created!!!!");
	}
	
	@After
	public void clearEnvironment(){
		String sql="DELETE FROM AVAILABILITY";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment Cleared!!!!");
	}
	
	@Test
	public void testCreateAvailability(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		Assert.assertEquals(availid, availability.getAvailabilityId());
		
		Assert.assertEquals(showid, availability.getShowId());
		
		Assert.assertEquals(movieid, availability.getMovieId());
		
		Assert.assertEquals(hallid, availability.getHallId());
		
		Assert.assertEquals("Cinepolis", availability.getHallName());
		
		Assert.assertEquals("Pune", availability.getVenue());
		
		Assert.assertEquals(20, availability.getPremiumSeat());
		
		Assert.assertEquals(30, availability.getGoldSeat());
		
		Assert.assertEquals(40, availability.getSilverSeat());
		
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date edate=cal.getTime();
		String expectedDate=sdfDate.format(edate);
		String actualDate=sdfDate.format(availability.getShowDate());
		Assert.assertEquals(expectedDate, actualDate);
		
		cal.set(Calendar.HOUR, 9);
		cal.set(Calendar.MINUTE, 30);
		Date etime=cal.getTime();
		String expectedTime=sdfTime.format(etime);
		String actualTime=sdfTime.format(availability.getStartTime());
		Assert.assertEquals(expectedTime, actualTime);
	}
	
	@Test
	public void testDeleteAvailabilityEntryById(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		System.out.println("Availability Id:"+availid);
		final String queryForCheck="SELECT * FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		/*String dateString="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;*/
		JdbcTemplate jdbcDate=JdbcTemplateFactory.getJdbcTemplate();
		/*Date checkDate=jdbcDate.queryForObject(dateString, Date.class);
		
		System.out.println("Date :"+checkDate);*/
		
		availabilityService.deleteAvailabilityEntryById(availid);
		
		//final String queryForCheck="SELECT * FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		List<Availability> listForCheck=jdbcDate.query(queryForCheck, new AvailabilityRowMapper());
		Assert.assertTrue(listForCheck.isEmpty());
	}
	
	@Test
	public void testDeleteAvailabilityByDate(){
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		final String dateString="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		Date checkDate=jdbcTemplate.queryForObject(dateString, Date.class);
		
		//System.out.println("Date:"+checkDate);
		String scheckDate=sdfDate.format(checkDate);
		try {
			Date cDate=sdfDate.parse(scheckDate);
			availabilityService.deleteAvailabilityEntryByDate(cDate);
			
			final String queryString="SELECT * FROM AVAILABILITY WHERE SHOW_DATE='"+cDate+"'";
			JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
			List<Availability> listById=jdbcForCheck.query(queryString, new AvailabilityRowMapper());
			Assert.assertTrue(listById.isEmpty());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testBuyPremiumSeat(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		final String queryDate="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryTime="SELECT START_TIME FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryNoOfPSeat="SELECT PREMIUM_SEAT FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		
		
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		Date showDate=jdbcTemplate.queryForObject(queryDate, Date.class);
		Date showTime=jdbcTemplate.queryForObject(queryTime, Date.class);
		int actualNoOfSeat=jdbcTemplate.queryForInt(queryNoOfPSeat);
		int noOfSeat=4;
		
		availabilityService.buyPremiumSeat(showDate, showTime, noOfSeat);
		
		int expectedNoOfPSeat=actualNoOfSeat-noOfSeat;
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		Assert.assertEquals(expectedNoOfPSeat, availability.getPremiumSeat());
		
	}
	
	@Test
	public void testBuyGoldSeat(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		final String queryDate="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryTime="SELECT START_TIME FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryNoOfGSeat="SELECT GOLD_SEAT FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		Date showDate=jdbcTemplate.queryForObject(queryDate, Date.class);
		Date showTime=jdbcTemplate.queryForObject(queryTime, Date.class);
		int actualNoOfGSeat=jdbcTemplate.queryForInt(queryNoOfGSeat);
		int noOfSeat=2;
		
		availabilityService.buyGoldSeat(showDate, showTime, noOfSeat);
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		int expectedNoOfGSeat=actualNoOfGSeat-noOfSeat;
		
		Assert.assertEquals(expectedNoOfGSeat, availability.getGoldSeat());
		
	}
	
	@Test
	public void testBuySilverSeat(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		final String queryDate="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryTime="SELECT START_TIME FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryNoOfSSeat="SELECT SILVER_SEAT FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		Date showDate=jdbcTemplate.queryForObject(queryDate, Date.class);
		Date showTime=jdbcTemplate.queryForObject(queryTime, Date.class);
		int actualNofOfSSeat=jdbcTemplate.queryForInt(queryNoOfSSeat);
		int noOfSeat=5;
		
		availabilityService.buySilverSeat(showDate, showTime, noOfSeat);
		
		int expectedNoOfSSeat=actualNofOfSSeat-noOfSeat;
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		Assert.assertEquals(expectedNoOfSSeat, availability.getSilverSeat());
	}
	
	@Test
	public void testCancelPremiumSeat(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		final String queryDate="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryTime="SELECT START_TIME FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryNoOfPSeat="SELECT PREMIUM_SEAT FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		Date showDate=jdbcTemplate.queryForObject(queryDate, Date.class);
		Date showTime=jdbcTemplate.queryForObject(queryTime, Date.class);
		int actualNoOfPSeat=jdbcTemplate.queryForInt(queryNoOfPSeat);
		int noOfSeat=2;
		
		availabilityService.cancelPremiumSeat(showDate, showTime, noOfSeat);
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		int expectedNoOfPSeat=actualNoOfPSeat+noOfSeat;
		
		Assert.assertEquals(expectedNoOfPSeat, availability.getPremiumSeat());
	}
	
	@Test
	public void testCancelGoldSeat(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		final String queryDate="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryTime="SELECT START_TIME FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryNoOfGSeat="SELECT GOLD_SEAT FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		
		JdbcTemplate jdbcTemplate= JdbcTemplateFactory.getJdbcTemplate();
		
		Date showDate=jdbcTemplate.queryForObject(queryDate, Date.class);
		Date showTime=jdbcTemplate.queryForObject(queryTime, Date.class);
		int actualNoOfGSeat=jdbcTemplate.queryForInt(queryNoOfGSeat);
		int noOfSeat=3;
		
		availabilityService.cancelGoldSeat(showDate, showTime, noOfSeat);
		
		int expectedNoOfGSeat=actualNoOfGSeat+noOfSeat;
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		Assert.assertEquals(expectedNoOfGSeat, availability.getGoldSeat());
	}
	
	@Test
	public void testCancelSilverSeat(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		final String queryDate="SELECT SHOW_DATE FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryTime="SELECT START_TIME FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		final String queryNoOfSSeat="SELECT SILVER_SEAT FROM AVAILABILITY WHERE AVAILABILITY_ID="+availid;
		
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		Date showDate=jdbcTemplate.queryForObject(queryDate, Date.class);
		Date showTime=jdbcTemplate.queryForObject(queryTime, Date.class);
		int actualNoOfSSeat=jdbcTemplate.queryForInt(queryNoOfSSeat);
		int noOfSeat=4;
		
		availabilityService.cancelSilverSeat(showDate, showTime, noOfSeat);
		
		int expectedNoOfSSeat=actualNoOfSSeat+noOfSeat;
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		Assert.assertEquals(expectedNoOfSSeat, availability.getSilverSeat());
	}
	
	@Test
	public void testLoadDetailsById(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		Availability availability=availabilityService.loadDetailsById(availid);
		
		Assert.assertEquals(availid, availability.getAvailabilityId());
		
		Assert.assertEquals(showid, availability.getShowId());
		
		Assert.assertEquals(movieid, availability.getMovieId());
		
		Assert.assertEquals(hallid, availability.getHallId());
		
		Assert.assertEquals("Cinepolis", availability.getHallName());
		
		Assert.assertEquals("Pune", availability.getVenue());
		
		Assert.assertEquals(20, availability.getPremiumSeat());
		
		Assert.assertEquals(30, availability.getGoldSeat());
		
		Assert.assertEquals(40, availability.getSilverSeat());
		
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date edate=cal.getTime();
		String expectedDate=sdfDate.format(edate);
		String actualDate=sdfDate.format(availability.getShowDate());
		Assert.assertEquals(expectedDate, actualDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date etime=cal.getTime();
		String expectedTime=sdfTime.format(etime);
		String actualTime=sdfTime.format(availability.getStartTime());
		Assert.assertEquals(expectedTime, actualTime);
	}
	
	@Test
	public void testLoadDetailsByDate(){
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
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
		
		int showid=showsService.saveShow(show);
		
		Availability avail=new Availability();
		avail.setShowId(showid);
		avail.setMovieId(movieid);
		avail.setHallId(hallid);
		avail.setHallName("Cinepolis");
		avail.setVenue("Pune");
		avail.setPremiumSeat(20);
		avail.setGoldSeat(30);
		avail.setSilverSeat(40);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date date=cal.getTime();
		avail.setShowDate(date);
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date time=cal.getTime();
		avail.setStartTime(time);
		
		int availid=availabilityService.createAvailability(avail);
		
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			cal.set(Calendar.DAY_OF_MONTH, 21);
			Date showDate=cal.getTime();
			String sshowDate=sdfDate.format(showDate);
			System.out.println("before date :"+sshowDate);
			Date ashowDate=df.parse(sshowDate);
			System.out.println("Date :"+ashowDate);
			List<Availability> availability=availabilityService.loadDetailsByDate(ashowDate);
			
			Assert.assertEquals(availid, availability.get(0).getAvailabilityId());
			
			Assert.assertEquals(showid, availability.get(0).getShowId());
			
			Assert.assertEquals(movieid, availability.get(0).getMovieId());
			
			Assert.assertEquals(hallid, availability.get(0).getHallId());
			
			Assert.assertEquals("Cinepolis", availability.get(0).getHallName());
			
			Assert.assertEquals("Pune", availability.get(0).getVenue());
			
			Assert.assertEquals(20, availability.get(0).getPremiumSeat());
			
			Assert.assertEquals(30, availability.get(0).getGoldSeat());
			
			Assert.assertEquals(40, availability.get(0).getSilverSeat());
			
			String expectedDate=sdfDate.format(showDate);
			String actualDate=sdfDate.format(availability.get(0).getShowDate());
			Assert.assertEquals(expectedDate, actualDate);
			
			cal.set(Calendar.HOUR_OF_DAY, 21);
			cal.set(Calendar.MINUTE, 30);
			Date etime=cal.getTime();
			String expectedTime=sdfTime.format(etime);
			String actualTime=sdfTime.format(availability.get(0).getStartTime());
			Assert.assertEquals(expectedTime, actualTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Date:"+showDate);
		
		
	
	}
}
