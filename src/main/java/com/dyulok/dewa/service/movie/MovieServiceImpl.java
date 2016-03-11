package com.dyulok.dewa.service.movie;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.movie.MovieDao;
//import com.dyulok.dewa.dao.movie.MovieDaoImpl;
import com.dyulok.dewa.model.movie.Movie;

@Transactional
public class MovieServiceImpl implements MovieService {

	private static final Logger logger=Logger.getLogger(MovieServiceImpl.class);
	
	@Autowired
	MovieDao movieDao;
	
	public static Logger getLogger() {
		return logger;
	}

	public MovieDao getMovieDao(){
		return movieDao;
	}
	
	public void setMovieDao(MovieDao movieDao){
		this.movieDao=movieDao;
	}
	
	@Override
	public int saveMovie(Movie movie) {
		// TODO Auto-generated method stub		
		int movieid=movieDao.addMovie(movie);
		logger.info("Movie Added!!!!!");
		return movieid;
	}

	@Override
	public Movie getMInfobyid(int movid) {
		// TODO Auto-generated method stub
		return movieDao.searchMoviebyid(movid);
	}

	@Override
	public Movie getMInfobyname(String name) {
		// TODO Auto-generated method stub
		return movieDao.searchMoviebyname(name);
	}

	@Override
	public List<Movie> getMInfobygenere(String genere) {
		// TODO Auto-generated method stub
		return movieDao.searchMoviebygen(genere);
	}

	@Override
	public List<Movie> getMInfobylanguage(String lang) {
		// TODO Auto-generated method stub
		return movieDao.searchMoviebylang(lang);
	}

	@Override
	public void deleteMovie(int movid) {
		// TODO Auto-generated method stub
		movieDao.deleteMovie(movid);
		logger.info("Movie Removed!!!!");
	}
	
}
