package com.dyulok.dewa.dao.movie;

import java.util.List;

import com.dyulok.dewa.model.movie.Movie;

public interface MovieDao {

	int addMovie(Movie movie);
	void deleteMovie(int movieid);
	void updateMovie(Movie movie);
	Movie searchMoviebyid(int movieid);
	Movie searchMoviebyname(String name);
	List<Movie> searchMoviebylang(String lang);
	List<Movie> searchMoviebygen(String genere);
}
