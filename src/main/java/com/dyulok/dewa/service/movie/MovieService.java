package com.dyulok.dewa.service.movie;

import java.util.List;

import com.dyulok.dewa.model.movie.Movie;

public interface MovieService {

	int saveMovie(Movie movie);
	Movie getMInfobyid(int movid);
	Movie getMInfobyname(String name);
	List<Movie> getMInfobygenere(String genere);
	List<Movie> getMInfobylanguage(String lang);
	void deleteMovie(int movid);
}
