package com.dyulok.dewa.dao.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.dyulok.dewa.dao.JdbcTemplateFactory;
import com.dyulok.dewa.model.movie.Movie;
import com.dyulok.dewa.model.movie.MovieRowMapper;

public class MovieDaoImpl implements MovieDao {

	@Override
	public int addMovie(Movie movie) {
		// TODO Auto-generated method stub
		final String sql="Insert into movie(MOVIE_ID,MOVIE_NAME,LANGUAGE,GENERE,TYPE,DURATION) values("+movie.getMovieId()+",'"+movie.getMovieName()+"','"+movie.getLanguage()+"','"+movie.getGenere()+"','"+movie.getType()+"',"+movie.getDuration()+")";
		
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		KeyHolder keyHolder= new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator(){

			@Override
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				// TODO Auto-generated method stub
				return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			
		},keyHolder);
		return keyHolder.getKey().intValue();
	}

	@Override
	public void deleteMovie(int movieid) {
		// TODO Auto-generated method stub
		String sql="Delete from movie where MOVIE_ID="+movieid+"";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql);
	}

	@Override
	public void updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		String sql="Update movie set MOVIE_ID=?,MOVIE_NAME=?,LANGUAGE=?,GENERE=?,TYPE=?,DURATION=?";
		Object[] params={movie.getMovieId(),movie.getMovieName(),movie.getLanguage(),movie.getGenere(),movie.getType(),movie.getDuration()};
		int[] types={Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		jdbcTemplate.update(sql, params, types);
	}

	@Override
	public Movie searchMoviebyid(int movieid) {
		// TODO Auto-generated method stub
		String sql="Select * from movie where MOVIE_ID="+movieid+"";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Movie> idMList=jdbcTemplate.query(sql, new MovieRowMapper());
		if(idMList==null||idMList.isEmpty()){
			return null;
		}
		return idMList.get(0);
	}

	@Override
	public Movie searchMoviebyname(String name) {
		// TODO Auto-generated method stub
		String sql="Select * from movie where MOVIE_NAME='"+name+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Movie> nameMList=jdbcTemplate.query(sql, new MovieRowMapper());
		if(nameMList==null||nameMList.isEmpty()){
			return null;
		}
		return nameMList.get(0);
	}

	@Override
	public Movie searchMoviebylang(String lang) {
		// TODO Auto-generated method stub
		String sql="Select * from movie where LANGUAGE='"+lang+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Movie> langMList=jdbcTemplate.query(sql, new MovieRowMapper());
		if(langMList==null||langMList.isEmpty()){
			return null;
		}
		return langMList.get(0);
	}

	@Override
	public Movie searchMoviebygen(String genere) {
		// TODO Auto-generated method stub
		String sql="Select * from movie where GENERE='"+genere+"'";
		JdbcTemplate jdbcTemplate=JdbcTemplateFactory.getJdbcTemplate();
		List<Movie> genMList=jdbcTemplate.query(sql, new MovieRowMapper());
		if(genMList==null||genMList.isEmpty()){
			return null;
		}
		return genMList.get(0);
	}
	
}
