package com.dyulok.dewa.service.shows;

/*import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.dyulok.dewa.model.hall.Hall;
import com.dyulok.dewa.model.movie.Movie;
import com.dyulok.dewa.model.shows.Show;
import com.dyulok.dewa.model.shows.ShowsRowMapper;
import com.dyulok.dewa.service.hall.HallService;
import com.dyulok.dewa.service.movie.MovieService;


@ContextConfiguration(locations="/com/dyulok/dewa/testbeans.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class ShowsServiceTest {

	@Autowired
	private ShowsService showsService;
	
	@Autowired
	private HallService hallService;
	
	@Autowired
	private MovieService movieService;
	
	@Before
	public void createEnvironment(){
		
		String sqls="DELETE FROM SHOWS";
		JdbcTemplate jdbcTemplates=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplates.update(sqls);
		
		String sqlm="DELETE FROM MOVIE";
		JdbcTemplate jdbcTemplatem=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplatem.update(sqlm);
		
		String sqlh="DELETE FROM HALL";
		JdbcTemplate jdbcTemplateh=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplateh.update(sqlh);
		
		System.out.println("Environment is set!!!!!");
	}
	
	@After
	public void clearEnvironment(){
		
		String sqls="DELETE FROM SHOWS";
		JdbcTemplate jdbcTemplates=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplates.update(sqls);
		
		String sqlm="DELETE FROM MOVIE";
		JdbcTemplate jdbcTemplatem=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplatem.update(sqlm);
		
		String sqlh="DELETE FROM HALL";
		JdbcTemplate jdbcTemplateh=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplateh.update(sqlh);
		
		System.out.println("Environment is cleared!!!!!");
	}
	
	@Test
	public void testSaveShow(){
		
		SimpleDateFormat sfdTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sfdDate=new SimpleDateFormat("YYYY-MM-dd");
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
//		Date today = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, 21);
		Date fromDate = cal.getTime();
		String fromdate=sfdDate.format(fromDate);
		
		cal.set(Calendar.DAY_OF_MONTH, 28);
		Date toDate = cal.getTime();
		String todate=sfdDate.format(toDate);
		
		cal.set(Calendar.HOUR_OF_DAY, 21);
		cal.set(Calendar.MINUTE, 30);
		Date startTime=cal.getTime();
		String starttime=sfdTime.format(startTime);
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 15);
		Date endTime=cal.getTime();
		String endtime=sfdTime.format(endTime);
		
//		if(startDate.before(today) && today.before(endDate)) {
			
//		}
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		Show shows=new Show();
		shows.setHallId(hallid);
		shows.setHallName(hall.getHallName());
		shows.setMovieId(movieid);
		shows.setMovieName(movie.getMovieName());
		shows.setStartTime(startTime);
		shows.setEndTime(endTime);
		shows.setFromDate(fromDate);
		shows.setToDate(toDate);
		shows.setPremiumPrice(180);
		shows.setGoldPrice(140);
		shows.setSilverPrice(100);
		//shows.getMovieId().add(movie);
		//shows.getHallId().add(hall);
		
		int showId=showsService.saveShow(shows);
		
		String sql="SELECT * FROM SHOWS WHERE SHOW_ID="+showId;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Show> showById=jdbcTemplate.query(sql, new ShowsRowMapper());
		
		Assert.assertEquals(showId, showById.get(0).getShowId());
		
		Assert.assertEquals(hallid,showById.get(0).getHallId());
		
		Assert.assertEquals("Cinepolis",showById.get(0).getHallName());
		
		Assert.assertEquals(movieid, showById.get(0).getMovieId());
		
		Assert.assertEquals("Deadpool",showById.get(0).getMovieName());
		
		String actualStartTime=sfdTime.format(showById.get(0).getStartTime());
		Assert.assertEquals(starttime, actualStartTime);
		
		String actualEndTime=sfdTime.format(showById.get(0).getEndTime());
		Assert.assertEquals(endtime, actualEndTime);
		
		String actualFromDate=sfdDate.format(showById.get(0).getFromDate());
		Assert.assertEquals(fromdate, actualFromDate);
		
		String actualToDate=sfdDate.format(showById.get(0).getToDate());
		Assert.assertEquals(todate, actualToDate);
		
		Assert.assertEquals(180.0, showById.get(0).getPremiumPrice(), 0.0);
		
		Assert.assertEquals(140.0, showById.get(0).getGoldPrice(),0.0);
		
		Assert.assertEquals(100.0, showById.get(0).getSilverPrice(),0.0);
	}
	
	@Test
	public void testDeleteShow(){
		final String sqlm="INSERT INTO MOVIE(MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION)VALUES('Deadpool','English','Action','UA',105)";
		final String sqlh="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Cinepolis','Pune',20,30,40)";
		final String sqls="INSERT INTO SHOWS(HALL_NAME,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES('Cinepolis','Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sqlm);
		jdbcTemplate.update(sqlh);
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		showsService.deleteShow(showid);
		
		String sqlForCheck="SELECT * FROM SHOWS WHERE SHOW_ID="+showid;
		List<Show> idList=jdbcTemplate.query(sqlForCheck, new ShowsRowMapper());
		Assert.assertTrue(idList.isEmpty());
	}
	
	@Test
	public void testUpdateShowToDate(){
		
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);

		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		final String sqls="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		SimpleDateFormat sfd=new SimpleDateFormat("YYYY-MM-dd");
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("IST"));
		
		Show show=showsService.loadDetailsByShowId(showid);
		cal.set(Calendar.DAY_OF_MONTH, 29);
		Date newToDate=cal.getTime();
		show.setToDate(newToDate);
		showsService.updateShowToDate(showid, newToDate);
		final String sqlForUpdate="SELECT TO_DATE FROM SHOWS WHERE SHOW_ID="+showid;
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		
		Date checkDate=jdbcForCheck.queryForObject(sqlForUpdate, Date.class);
		
		String actualDate=sfd.format(checkDate);
		String expectedDate=sfd.format(show.getToDate());
		
		Assert.assertEquals(expectedDate, actualDate);
	}
	
	@Test
	public void testUpdateShowTime(){
		
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
		
		final String sqlst="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqlst, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		SimpleDateFormat sfd=new SimpleDateFormat("HH:mm:ss"); 
		
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.set(Calendar.HOUR_OF_DAY, 18);
		cal.set(Calendar.MINUTE, 30);
		Date startTime=cal.getTime();
		
		cal.set(Calendar.HOUR_OF_DAY, 20);
		cal.set(Calendar.MINUTE, 15);
		Date endTime=cal.getTime();
		
		Show show=showsService.loadDetailsByShowId(showid);
		show.setStartTime(startTime);
		show.setEndTime(endTime);
		
		showsService.updateShowTime(showid, startTime, endTime);
		
		final String sqlForStartCheck="SELECT START_TIME FROM SHOWS WHERE SHOW_ID="+showid;
		final String sqlForEndCheck="SELECT END_TIME FROM SHOWS WHERE SHOW_ID="+showid;
		
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		
		Date startTimeCheck=jdbcForCheck.queryForObject(sqlForStartCheck, Date.class);
		
		String actualStartTime=sfd.format(startTimeCheck);
		String expectedStartTime=sfd.format(show.getStartTime());
		
		Assert.assertEquals(expectedStartTime, actualStartTime);
		
		Date endTimeCheck=jdbcForCheck.queryForObject(sqlForEndCheck, Date.class);
		
		String actualEndTime=sfd.format(endTimeCheck);
		String expectedEndTime=sfd.format(show.getEndTime());
		
		Assert.assertEquals(expectedEndTime, actualEndTime);
	}
	
	@Test
	public void testUpdateSeatPrice(){
		
		final String sqlm="INSERT INTO MOVIE(MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION)VALUES('Deadpool','English','Action','A',105)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqlm,Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		int movieid=keyHolder.getKey().intValue();
		
		final String sqlh="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Cinepolis','Pune',20,30,40)";
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqlh, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		int hallid=keyHolder.getKey().intValue();
		
		final String sqls="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		float newPremiumPrice=160f;
		float newGoldPrice=120f;
		float newSilverPrice=80f;
		
		Show show=showsService.loadDetailsByShowId(showid);
		show.setPremiumPrice(newPremiumPrice);
		show.setGoldPrice(newGoldPrice);
		show.setSilverPrice(newSilverPrice);
		
		showsService.updateSeatPrice(showid, newGoldPrice, newSilverPrice, newPremiumPrice);
		
		final String sql="SELECT * FROM SHOWS WHERE SHOW_ID="+showid;
		
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		List<Show> listPrice=jdbcForCheck.query(sql, new ShowsRowMapper());
		
		Assert.assertEquals(show.getPremiumPrice(), listPrice.get(0).getPremiumPrice(), 0.0);
		Assert.assertEquals(show.getGoldPrice(), listPrice.get(0).getGoldPrice(), 0.0);
		Assert.assertEquals(show.getSilverPrice(), listPrice.get(0).getSilverPrice(), 0.0);
	}
	
	@Test
	public void testLoadDetailsByShowId(){
		
		SimpleDateFormat sfdTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sfdDate=new SimpleDateFormat("YYYY-MM-dd");
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		final String sqls="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		Show show=showsService.loadDetailsByShowId(showid);
		
		Assert.assertEquals(showid, show.getShowId());
		
		Assert.assertEquals(hallid, show.getHallId());
		
		Assert.assertEquals("Cinepolis", show.getHallName());
		
		Assert.assertEquals(movieid, show.getMovieId());
		
		Assert.assertEquals("Deadpool", show.getMovieName());
		
		String expectedStartTime="21:30:00";
		String actualStartTime=sfdTime.format(show.getStartTime());
		Assert.assertEquals(expectedStartTime, actualStartTime);
		
		String expectedEndTime="23:15:00";
		String actualEndTime=sfdTime.format(show.getEndTime());
		Assert.assertEquals(expectedEndTime, actualEndTime);
		
		String expectedFromDate="2016-02-21";
		String actualFromDate=sfdDate.format(show.getFromDate());
		Assert.assertEquals(expectedFromDate, actualFromDate);
		
		String expectedToDate="2016-02-28";
		String actualToDate=sfdDate.format(show.getToDate());
		Assert.assertEquals(expectedToDate, actualToDate);
		
		Assert.assertEquals(180.0, show.getPremiumPrice(), 0.0);
		
		Assert.assertEquals(140.0, show.getGoldPrice(),0.0);
		
		Assert.assertEquals(100.00, show.getSilverPrice(), 0.0);
	}
	
	@Test
	public void testLoadDetailsByHallName(){

		SimpleDateFormat sfdTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sfdDate=new SimpleDateFormat("YYYY-MM-dd");
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		final String sqls="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		final String sqlForCheck="SELECT HALL_NAME FROM SHOWS WHERE SHOW_ID="+showid;
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		String hallName=jdbcForCheck.queryForObject(sqlForCheck, String.class);
		
		List<Show> show=showsService.loadDetailsByHallName(hallName);
		
		Assert.assertEquals(showid, show.get(0).getShowId());
		
		Assert.assertEquals(hallid, show.get(0).getHallId());
		
		Assert.assertEquals(hallName, show.get(0).getHallName());
		
		Assert.assertEquals(movieid, show.get(0).getMovieId());
		
		Assert.assertEquals("Deadpool", show.get(0).getMovieName());
		
		String expectedStartTime="21:30:00";
		String actualStartTime=sfdTime.format(show.get(0).getStartTime());
		Assert.assertEquals(expectedStartTime, actualStartTime);
		
		String expectedEndTime="23:15:00";
		String actualEndTime=sfdTime.format(show.get(0).getEndTime());
		Assert.assertEquals(expectedEndTime, actualEndTime);
		
		String expectedFromDate="2016-02-21";
		String actualFromDate=sfdDate.format(show.get(0).getFromDate());
		Assert.assertEquals(expectedFromDate, actualFromDate);
		
		String expectedToDate="2016-02-28";
		String actualToDate=sfdDate.format(show.get(0).getToDate());
		Assert.assertEquals(expectedToDate, actualToDate);
		
		Assert.assertEquals(180.0, show.get(0).getPremiumPrice(), 0.0);
		
		Assert.assertEquals(140.0, show.get(0).getGoldPrice(),0.0);
		
		Assert.assertEquals(100.00, show.get(0).getSilverPrice(), 0.0);
	}
	
	@Test
	public void testLoadDetailsByMovieName(){
		
		SimpleDateFormat sfdTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sfdDate=new SimpleDateFormat("YYYY-MM-dd");
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		final String sqls="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		String sqlForCheck="SELECT MOVIE_NAME FROM SHOWS WHERE SHOW_ID="+showid;
		
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		String movieName=jdbcForCheck.queryForObject(sqlForCheck, String.class);
		
		List<Show> show=showsService.loadDetailsByMovieName(movieName);
		
		Assert.assertEquals(showid, show.get(0).getShowId());
		
		Assert.assertEquals(hallid, show.get(0).getHallId());
		
		Assert.assertEquals("Cinepolis", show.get(0).getHallName());
		
		Assert.assertEquals(movieid, show.get(0).getMovieId());
		
		Assert.assertEquals(movieName, show.get(0).getMovieName());
		
		String expectedStartTime="21:30:00";
		String actualStartTime=sfdTime.format(show.get(0).getStartTime());
		Assert.assertEquals(expectedStartTime, actualStartTime);
		
		String expectedEndTime="23:15:00";
		String actualEndTime=sfdTime.format(show.get(0).getEndTime());
		Assert.assertEquals(expectedEndTime, actualEndTime);
		
		String expectedFromDate="2016-02-21";
		String actualFromDate=sfdDate.format(show.get(0).getFromDate());
		Assert.assertEquals(expectedFromDate, actualFromDate);
		
		String expectedToDate="2016-02-28";
		String actualToDate=sfdDate.format(show.get(0).getToDate());
		Assert.assertEquals(expectedToDate, actualToDate);
		
		Assert.assertEquals(180.0, show.get(0).getPremiumPrice(), 0.0);
		
		Assert.assertEquals(140.0, show.get(0).getGoldPrice(),0.0);
		
		Assert.assertEquals(100.00, show.get(0).getSilverPrice(), 0.0);
	}
	
	@Test
	public void testLoadDetailsByStartTime(){
		
		SimpleDateFormat sfdTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sfdDate=new SimpleDateFormat("YYYY-MM-dd");
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		final String sqls="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-02-21','2016-02-28',180,140,100)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		final String sqlForCheck="SELECT START_TIME FROM SHOWS WHERE SHOW_ID="+showid;
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		
		Date startTime=jdbcForCheck.queryForObject(sqlForCheck, Date.class);
		
		List<Show> show=showsService.loadDetailsByStartTime(startTime);
		
		Assert.assertEquals(showid, show.get(0).getShowId());
		
		Assert.assertEquals(hallid, show.get(0).getHallId());
		
		Assert.assertEquals("Cinepolis", show.get(0).getHallName());
		
		Assert.assertEquals(movieid, show.get(0).getMovieId());
		
		Assert.assertEquals("Deadpool", show.get(0).getMovieName());
		
		String expectedStartTime=sfdTime.format(startTime);
		String actualStartTime=sfdTime.format(show.get(0).getStartTime());
		Assert.assertEquals(expectedStartTime, actualStartTime);
		
		String expectedEndTime="23:15:00";
		String actualEndTime=sfdTime.format(show.get(0).getEndTime());
		Assert.assertEquals(expectedEndTime, actualEndTime);
		
		String expectedFromDate="2016-02-21";
		String actualFromDate=sfdDate.format(show.get(0).getFromDate());
		Assert.assertEquals(expectedFromDate, actualFromDate);
		
		String expectedToDate="2016-02-28";
		String actualToDate=sfdDate.format(show.get(0).getToDate());
		Assert.assertEquals(expectedToDate, actualToDate);
		
		Assert.assertEquals(180.0, show.get(0).getPremiumPrice(), 0.0);
		
		Assert.assertEquals(140.0, show.get(0).getGoldPrice(),0.0);
		
		Assert.assertEquals(100.00, show.get(0).getSilverPrice(), 0.0);
	}
	
	@Test
	public void testLoadDetailsByDate(){
		
		SimpleDateFormat sfdTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sfdDate=new SimpleDateFormat("YYYY-MM-dd");
		
		Hall hall=new Hall();
		hall.setHallName("Cinepolis");
		hall.setVenue("Pune");
		hall.setPremiumSeat(20);
		hall.setGoldSeat(30);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		Movie movie=new Movie();
		movie.setMovieName("Deadpool");
		movie.setLanguage("English");
		movie.setGenere("Action");
		movie.setType("A");
		movie.setDuration(105);
		
		int movieid=movieService.saveMovie(movie);
		
		final String sqls="INSERT INTO SHOWS(HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+hallid+",'Cinepolis',"+movieid+",'Deadpool','21:30:00','23:15:00','2016-03-21','2016-03-28',180,140,100)";
		
		KeyHolder keyHolder=new GeneratedKeyHolder();
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sqls, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int showid=keyHolder.getKey().intValue();
		
		Calendar cal=Calendar.getInstance(TimeZone.getTimeZone("IST"));
		cal.set(Calendar.DAY_OF_MONTH, 24);
		Date today=cal.getTime();
		
		Show show=showsService.loadDetailsByDate(today);
		
		Assert.assertEquals(showid, show.getShowId());
		
		Assert.assertEquals(hallid, show.getHallId());
		
		Assert.assertEquals("Cinepolis", show.getHallName());
		
		Assert.assertEquals(movieid, show.getMovieId());
		
		Assert.assertEquals("Deadpool", show.getMovieName());
		
		String expectedStartTime="21:30:00";
		String actualStartTime=sfdTime.format(show.getStartTime());
		Assert.assertEquals(expectedStartTime, actualStartTime);
		
		String expectedEndTime="23:15:00";
		String actualEndTime=sfdTime.format(show.getEndTime());
		Assert.assertEquals(expectedEndTime, actualEndTime);
		
		String expectedFromDate="2016-03-21";
		String actualFromDate=sfdDate.format(show.getFromDate());
		Assert.assertEquals(expectedFromDate, actualFromDate);
		
		String expectedToDate="2016-03-28";
		String actualToDate=sfdDate.format(show.getToDate());
		Assert.assertEquals(expectedToDate, actualToDate);
		
		Assert.assertEquals(180.0, show.getPremiumPrice(), 0.0);
		
		Assert.assertEquals(140.0, show.getGoldPrice(),0.0);
		
		Assert.assertEquals(100.00, show.getSilverPrice(), 0.0); 
	}
}
