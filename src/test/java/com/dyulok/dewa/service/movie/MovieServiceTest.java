package com.dyulok.dewa.service.movie;

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
import com.dyulok.dewa.model.movie.Movie;
import com.dyulok.dewa.model.movie.MovieRowMapper;

@ContextConfiguration(locations="/com/dyulok/dewa/testbeans.xml")
@RunWith(SpringJUnit4ClassRunner.class)

public class MovieServiceTest {
	
	@Autowired
	private MovieService movieService;

	@Before
	public void createEnvironment(){
		String sql="DELETE FROM MOVIE";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment is Set!!!!");
	}
	
	@After
	public void clearEnvironment(){
		String sql="DELETE FROM MOVIE";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
		
		System.out.println("Environment is cleared!!!!");
	}
	
	@Test
	public void testSaveMovie(){
		Movie movie=new Movie();
		movie.setMovieName("Interstellar");
		movie.setLanguage("English");
		movie.setGenere("Science Fiction");
		movie.setType("UA");
		movie.setDuration(169);
		
		int movid=movieService.saveMovie(movie);
		
		String sql="SELECT * FROM MOVIE WHERE MOVIE_ID="+movid;
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Movie> listMovie=jdbcTemplate.query(sql, new MovieRowMapper());
		Assert.assertEquals(movid, listMovie.get(0).getMovieId());
		Assert.assertEquals("Interstellar", listMovie.get(0).getMovieName());
		Assert.assertEquals("English", listMovie.get(0).getLanguage());
		Assert.assertEquals("Science Fiction", listMovie.get(0).getGenere());
		Assert.assertEquals("UA", listMovie.get(0).getType());
		Assert.assertEquals(169, listMovie.get(0).getDuration());
	}
	
	@Test
	public void testDeleteMovie(){
		final String sql="INSERT INTO MOVIE(MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION) VALUES ('Interstellar','English','Science Fiction','UA',169)";
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
		
		int movid=keyHolder.getKey().intValue();
		
		movieService.deleteMovie(movid);
		String sqlforcheck="SELECT * FROM MOVIE WHERE MOVIE_ID="+movid;
		JdbcTemplate jdbcForCheckTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Movie> listMovieDetails=jdbcForCheckTemplate.query(sqlforcheck, new MovieRowMapper());
		Assert.assertTrue(listMovieDetails.isEmpty());
	}
	
	@Test
	public void testGetMovieInfoById(){
		final String sql="INSERT INTO MOVIE(MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION) VALUES ('Interstellar','English','Science Fiction','UA',169)";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder= new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		}, keyHolder);
		
		int movid=keyHolder.getKey().intValue();
		
		Movie movie=movieService.getMInfobyid(movid);
		Assert.assertEquals(movid, movie.getMovieId());
		Assert.assertEquals("Interstellar",movie.getMovieName());
		Assert.assertEquals("English",movie.getLanguage());
		Assert.assertEquals("Science Fiction", movie.getGenere());
		Assert.assertEquals("UA",movie.getType());
		Assert.assertEquals(169,movie.getDuration());
	}
	
	@Test
	public void testGetMovieInfoByName(){
		final String sql="INSERT INTO MOVIE(MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION) VALUES ('Interstellar','English','Science Fiction','UA',169)";
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
		
		int movid=keyHolder.getKey().intValue();
		
		String sqlforname="SELECT MOVIE_NAME FROM MOVIE WHERE MOVIE_ID="+movid;
		
		JdbcTemplate jdbcForMovieDetailsByName=JdbcTemplateFactory.getJdbcTemplate();
		String name=jdbcForMovieDetailsByName.queryForObject(sqlforname, String.class);
		
		Movie movie=movieService.getMInfobyname(name);
		Assert.assertEquals(movid,movie.getMovieId());
		Assert.assertEquals(name, movie.getMovieName());
		Assert.assertEquals("English", movie.getLanguage());
		Assert.assertEquals("Science Fiction",movie.getGenere());
		Assert.assertEquals("UA", movie.getType());
		Assert.assertEquals(169,movie.getDuration());
	}
	
	@Test
	public void testGetMovieInfoByLanguage(){
		final String sql="INSERT INTO MOVIE(MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION) VALUES ('Interstellar','English','Science Fiction','UA',169)";
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
		
		int movid=keyHolder.getKey().intValue();
		
		String sqlforlanguage="SELECT LANGUAGE FROM MOVIE WHERE MOVIE_ID="+movid;
		
		JdbcTemplate jdbcForMovieDetailsByLanguage=JdbcTemplateFactory.getJdbcTemplate();
		String language=jdbcForMovieDetailsByLanguage.queryForObject(sqlforlanguage, String.class);
		
		List<Movie> movie=movieService.getMInfobylanguage(language);
		Assert.assertEquals(movid,movie.get(0).getMovieId());
		Assert.assertEquals("Interstellar", movie.get(0).getMovieName());
		Assert.assertEquals(language, movie.get(0).getLanguage());
		Assert.assertEquals("Science Fiction", movie.get(0).getGenere());
		Assert.assertEquals("UA",movie.get(0).getType());
		Assert.assertEquals(169,movie.get(0).getDuration());
	}
	
	@Test
	public void testGetMovieInfoByGenere(){
		final String sql="INSERT INTO MOVIE(MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION) VALUES ('Interstellar','English','Science Fiction','UA',169)";
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
		
		int movid=keyHolder.getKey().intValue();
		
		String sqlforgenere="SELECT GENERE FROM MOVIE WHERE MOVIE_ID="+movid;
		
		JdbcTemplate jdbcForMovieDetailsByGenere=JdbcTemplateFactory.getJdbcTemplate();
		String genere=jdbcForMovieDetailsByGenere.queryForObject(sqlforgenere, String.class);
		
		List<Movie> movie=movieService.getMInfobygenere(genere);
		Assert.assertEquals(movid, movie.get(0).getMovieId());
		Assert.assertEquals("Interstellar", movie.get(0).getMovieName());
		Assert.assertEquals("English", movie.get(0).getLanguage());
		Assert.assertEquals(genere, movie.get(0).getGenere());
		Assert.assertEquals("UA", movie.get(0).getType());
		Assert.assertEquals(169,movie.get(0).getDuration());
	}
}
