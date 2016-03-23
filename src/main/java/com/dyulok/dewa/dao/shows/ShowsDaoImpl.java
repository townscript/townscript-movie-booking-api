package com.dyulok.dewa.dao.shows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.dyulok.dewa.dao.JdbcTemplateFactory;
import com.dyulok.dewa.model.shows.Show;
import com.dyulok.dewa.model.shows.ShowsRowMapper;

public class ShowsDaoImpl implements ShowsDao {

	@Override
	public int saveShow(Show shows) {
		// TODO Auto-generated method stub
		final String sql="INSERT INTO SHOWS(SHOW_ID,HALL_ID,HALL_NAME,MOVIE_ID,MOVIE_NAME,START_TIME,END_TIME,FROM_DATE,TO_DATE,PREMIUM_SEAT_PRICE,GOLD_SEAT_PRICE,SILVER_SEAT_PRICE)VALUES("+shows.getShowId()+","+shows.getHallId()+","+shows.getHallId()+",'"+shows.getHallName()+"',"+shows.getMovieId()+",'"+shows.getMovieName()+"',TIME("+shows.getStartTime()+"),TIME("+shows.getEndTime()+"),'"+shows.getFromDate()+"','"+shows.getToDate()+"',"+shows.getPremiumPrice()+","+shows.getGoldPrice()+","+shows.getSilverPrice()+")";
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
		return keyHolder.getKey().intValue();
	}

	@Override
	public void deleteShow(int showId) {
		// TODO Auto-generated method stub
		final String sql="DELETE FROM SHOWS WHERE SHOW_ID="+showId;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
	}

	@Override
	public void updateShow(Show show) {
		// TODO Auto-generated method stub
		final String sql="UPDATE SHOWS SET SHOW_ID=?,HALL_ID=?,HALL_NAME=?,MOVIE_ID=?,MOVIE_NAME=?,START_TIME=?,END_TIME=?,FROM_DATE=?,TO_DATE=?,PREMIUM_SEAT_PRICE=?,GOLD_SEAT_PRICE=?,SILVER_SEAT_PRICE=?";
		Object[] params={show.getShowId(),show.getHallId(),show.getHallName(),show.getMovieId(),show.getMovieName(),show.getStartTime(),show.getEndTime(),show.getFromDate(),show.getToDate(),show.getPremiumPrice(),show.getGoldPrice(),show.getSilverPrice()};
		int[] types={Types.INTEGER,Types.INTEGER,Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.TIME,Types.TIME,Types.DATE,Types.DATE,Types.FLOAT,Types.FLOAT,Types.FLOAT};
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public void updateShowPrice(Show show) {
		// TODO Auto-generated method stub
		final String sql="UPDATE SHOWS SET PREMIUM_SEAT_PRICE=?,GOLD_SEAT_PRICE=?,SILVER_SEAT_PRICE=?";
		Object[] params={show.getPremiumPrice(),show.getGoldPrice(),show.getSilverPrice()};
		int[] types={Types.FLOAT,Types.FLOAT,Types.FLOAT};
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public void updateShowToDate(Show show) {
		// TODO Auto-generated method stub
		final String sql="UPDATE SHOWS SET TO_DATE=?";
		Object[] params={show.getToDate()};
		int[] types={Types.DATE};
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public void updateShowTime(Show show) {
		// TODO Auto-generated method stub
		final String sql="UPDATE SHOWS SET START_TIME=?,END_TIME=?";
		Object[] params={show.getStartTime(),show.getEndTime()};
		int[] types={Types.TIME,Types.TIME};
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public Show searchShowById(int showId) {
		// TODO Auto-generated method stub
		final String sql="SELECT * FROM SHOWS WHERE SHOW_ID="+showId;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Show> listShowsById=jdbcTemplate.query(sql, new ShowsRowMapper());
		if(listShowsById==null){
			return null;
		}
		return listShowsById.get(0);
	}

	@Override
	public List<Show> searchShowByHallName(String hallName) {
		// TODO Auto-generated method stub
		final String sql="SELECT * FROM SHOWS WHERE HALL_NAME LIKE '"+hallName+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Show> listShowByHall=jdbcTemplate.query(sql, new ShowsRowMapper());
		if(listShowByHall==null){
			return null;
		}
		return listShowByHall;
	}

	@Override
	public List<Show> searchShowByMovieName(String movieName) {
		// TODO Auto-generated method stub
		final String sql="SELECT * FROM SHOWS WHERE MOVIE_NAME LIKE '"+movieName+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Show> listShowByMovieName=jdbcTemplate.query(sql, new ShowsRowMapper());
		if(listShowByMovieName==null){
			return null;
		}
		return listShowByMovieName;
	}

	@Override
	public List<Show> searchShowByStartTime(Date startTime) {
		// TODO Auto-generated method stub
		final String sql="SELECT * FROM ";
		return null;
	}

	@Override
	public Show searchShowByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
}
