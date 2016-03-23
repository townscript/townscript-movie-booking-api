package com.dyulok.dewa.service.hall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
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
import com.dyulok.dewa.model.hall.HallRowMapper;

@ContextConfiguration(locations={"/com/dyulok/dewa/testbeans.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class HallServiceTest {

	@Autowired
	private HallService hallService;
	
	@Before
	public void createEnvironment(){
		String sql="DELETE FROM HALL";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment is Created!!!!");
	}
	
	@After
	public void clearEnvironment(){
		String sql="DELETE FROM HALL";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment is Cleaned!!!!");
	}
	
	@Test
	public void testSaveHall(){
		Hall hall=new Hall();
		hall.setHallName("Citypride Kothrud");
		hall.setVenue("Kothrud");
		hall.setPremiumSeat(25);
		hall.setGoldSeat(35);
		hall.setSilverSeat(40);
		
		int hallid=hallService.saveHall(hall);
		
		String sql="SELECT * FROM HALL WHERE HALL_ID="+hallid;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Hall> listHall=jdbcTemplate.query(sql, new HallRowMapper());
		Assert.assertEquals(hallid, listHall.get(0).getHallId());
		Assert.assertEquals("Citypride Kothrud",listHall.get(0).getHallName());
		Assert.assertEquals("Kothrud", listHall.get(0).getVenue());
		Assert.assertEquals(25, listHall.get(0).getPremiumSeat());
		Assert.assertEquals(35, listHall.get(0).getGoldSeat());
		Assert.assertEquals(40, listHall.get(0).getSilverSeat());
	}
	
	@Test
	public void testUpdateHallName(){
		final String sql="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Citypride Kothrud','Kothrud',25,35,40)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int hallid=keyHolder.getKey().intValue();
		
		String newHallName="Inox";
		Hall hall=hallService.loadHallInfoById(hallid);
		hall.setHallName(newHallName);
		hallService.updateHallName(hallid, newHallName);
		String sqlforCheck="SELECT HALL_NAME FROM HALL WHERE HALL_ID="+hallid;
		
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		String hallname=jdbcForCheck.queryForObject(sqlforCheck, String.class);
		
		Assert.assertEquals(hallname, hall.getHallName());
	}
	
	@Test
	public void testLoadHallInfoById(){
		final String sql="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Citypride Kothrud','Kothrud',25,35,40)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int hallid=keyHolder.getKey().intValue();
		
		Hall hall=hallService.loadHallInfoById(hallid);
		Assert.assertEquals(hallid,hall.getHallId());
		Assert.assertEquals("Citypride Kothrud", hall.getHallName());
		Assert.assertEquals("Kothrud",hall.getVenue());
		Assert.assertEquals(25,hall.getPremiumSeat());
		Assert.assertEquals(35,hall.getGoldSeat());
		Assert.assertEquals(40,hall.getSilverSeat());
	}
	
	@Test
	public void testLoadHallInfoByName(){
		final String sql="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Citypride Kothrud','Kothrud',25,35,40)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int hallid=keyHolder.getKey().intValue();
		
		String sqlForName="SELECT HALL_NAME FROM HALL WHERE HALL_ID="+hallid;
		JdbcTemplate jdbcForName=JdbcTemplateFactory.getJdbcTemplate();
		
		String hallName=jdbcForName.queryForObject(sqlForName, String.class);
		
		List<Hall> hall=hallService.loadHallInfoByName(hallName);
		Assert.assertEquals(hallid, hall.get(0).getHallId());
		Assert.assertEquals(hallName, hall.get(0).getHallName());
		Assert.assertEquals("Kothrud",hall.get(0).getVenue());
		Assert.assertEquals(25, hall.get(0).getPremiumSeat());
		Assert.assertEquals(35, hall.get(0).getGoldSeat());
		Assert.assertEquals(40, hall.get(0).getSilverSeat());
	}
	
	@Test
	public void testLoadHallInfoByVenue(){
		final String sql="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Citypride Kothrud','Kothrud',25,35,40)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int hallid=keyHolder.getKey().intValue();
		
		String sqlForVenue="SELECT VENUE FROM HALL WHERE HALL_ID="+hallid;
		JdbcTemplate jdbcForVenue=JdbcTemplateFactory.getJdbcTemplate();
		
		String hallVenue=jdbcForVenue.queryForObject(sqlForVenue, String.class);
		
		List<Hall> hall=hallService.loadHallInfoByVenue(hallVenue);
		Assert.assertEquals(hallid, hall.get(0).getHallId());
		Assert.assertEquals("Citypride Kothrud",hall.get(0).getHallName());
		Assert.assertEquals(hallVenue,hall.get(0).getVenue());
		Assert.assertEquals(25, hall.get(0).getPremiumSeat());
		Assert.assertEquals(35,hall.get(0).getGoldSeat());
		Assert.assertEquals(40, hall.get(0).getSilverSeat());
	}
	
	@Test
	public void testGetSeatDetailsById(){
		final String sql="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Citypride Kothrud','Kothrud',25,35,40)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int hallid=keyHolder.getKey().intValue();
		
		Hall hall=hallService.getSeatDetailsById(hallid);
		Assert.assertEquals(25,hall.getPremiumSeat());
		Assert.assertEquals(35, hall.getGoldSeat());
		Assert.assertEquals(40,hall.getSilverSeat());
	}
	
	@Test
	public void testGetSeatDetailsByName(){
		final String sql="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Citypride Kothrud','Kothrud',25,35,40)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int hallid=keyHolder.getKey().intValue();
		
		String sqlForName="SELECT HALL_NAME FROM HALL WHERE HALL_ID="+hallid;
		JdbcTemplate jdbcForName=JdbcTemplateFactory.getJdbcTemplate();
		
		String hallName=jdbcForName.queryForObject(sqlForName, String.class);
		List<Hall> hall=hallService.getSeatDetailsByName(hallName);
		
		Assert.assertEquals(25,hall.get(0).getPremiumSeat());
		Assert.assertEquals(35, hall.get(0).getGoldSeat());
		Assert.assertEquals(40,hall.get(0).getSilverSeat());
	}
	
	@Test
	public void testDeleteHall(){
		final String sql="INSERT INTO HALL(HALL_NAME,VENUE,PREMIUM_SEAT,GOLD_SEAT,SILVER_SEAT)VALUES('Citypride Kothrud','Kothrud',25,35,40)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder=new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int hallid=keyHolder.getKey().intValue();
		
		hallService.deleteHall(hallid);
		final String queryString="SELECT * FROM HALL WHERE HALL_ID="+hallid;
		JdbcTemplate jdbcForCheck=JdbcTemplateFactory.getJdbcTemplate();
		List<Hall> idList=jdbcForCheck.query(queryString, new HallRowMapper());
		Assert.assertTrue(idList.isEmpty());
	}
}
