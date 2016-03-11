package com.dyulok.dewa.model.movie;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MovieRowMapper implements RowMapper<Movie>{

	@Override
	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Movie allMov=new Movie();
		allMov.setMovieId(rs.getInt("MOVIE_ID"));
		allMov.setMovieName(rs.getString("MOVIE_NAME"));
		allMov.setLanguage(rs.getString("LANGUAGE"));
		allMov.setGenere(rs.getString("GENERE"));
		allMov.setType(rs.getString("TYPE"));
		allMov.setDuration(rs.getInt("DURATION"));
		return allMov;
	}

}
