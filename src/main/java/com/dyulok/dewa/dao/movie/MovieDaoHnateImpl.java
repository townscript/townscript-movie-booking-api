package com.dyulok.dewa.dao.movie;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import com.dyulok.dewa.model.movie.Movie;

public class MovieDaoHnateImpl extends HibernateDaoSupport implements MovieDao {

	@Override
	public int addMovie(Movie movie) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(movie);
		int movieId=movie.getMovieId();
		return movieId;
	}

	@Override
	public void deleteMovie(int movieid) {
		// TODO Auto-generated method stub
		Movie movie=new Movie();
		movie.setMovieId(movieid);
		getHibernateTemplate().delete(movie);
	}

	@Override
	public void updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(movie);
	}

	@Override
	public Movie searchMovieById(int movieid) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Movie.class.getName()+" WHERE movieId = :movieid";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("movieid", movieid);
		
		List<Movie> idList=query.list();
		return idList.get(0);
	}

	@Override
	public Movie searchMovieByName(String name) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Movie.class.getName()+" WHERE movieName LIKE :name";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("name", name);
		
		List<Movie> nameList=query.list();
		return nameList.get(0);
	}

	@Override
	public List<Movie> searchMovieByLang(String lang) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Movie.class.getName()+" WHERE language LIKE :lang";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("lang", lang);
		
		List<Movie> languageList=query.list();
		return languageList;
	}

	@Override
	public List<Movie> searchMovieByGen(String genere) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Movie.class.getName()+" WHERE genere LIKE :genere";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("genere", genere);
		
		List<Movie> genereList=query.list();
		return genereList;
	}

}
