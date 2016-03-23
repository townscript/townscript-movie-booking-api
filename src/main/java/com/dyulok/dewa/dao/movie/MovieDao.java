package com.dyulok.dewa.dao.movie;

import java.util.List;

import com.dyulok.dewa.model.movie.Movie;

public interface MovieDao {

	int addMovie(Movie movie);
	void deleteMovie(int movieid);
	void updateMovie(Movie movie);
	Movie searchMovieById(int movieid);
	Movie searchMovieByName(String name);
	List<Movie> searchMovieByLang(String lang);
	List<Movie> searchMovieByGen(String genere);
}
