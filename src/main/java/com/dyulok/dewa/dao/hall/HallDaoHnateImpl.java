package com.dyulok.dewa.dao.hall;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.model.hall.Hall;
//import com.dyulok.dewa.model.movie.Movie;

@Transactional
public class HallDaoHnateImpl extends HibernateDaoSupport implements HallDao {

	@Override
	public int addHall(Hall hall) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(hall);
		int hallId=hall.getHallId();
		return hallId;
	}

	@Override
	public void deleteHall(int hallId) {
		// TODO Auto-generated method stub
		Hall hall=new Hall();
		hall.setHallId(hallId);
		getHibernateTemplate().delete(hall);
	}

	@Override
	public void updateHall(Hall hall) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(hall);
	}

	@Override
	public Hall loadHallbyid(int hallId) {
		// TODO Auto-generated method stub
		String stringQuery="FROM "+Hall.class.getName()+" WHERE hallId = :hallId";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(stringQuery);
		query.setParameter("hallId", hallId);
		
		List<Hall> hallIdList=query.list();
		return hallIdList.get(0);
	}

	@Override
	public List<Hall> loadHallbyName(String hallName) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Hall.class.getName()+" WHERE hallName LIKE :hallName";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("hallName", hallName);
		
		List<Hall> hallNameList=query.list();
		return hallNameList;
	}

	@Override
	public List<Hall> loadHallbyVenue(String hallVenue) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Hall.class.getName()+" WHERE venue LIKE :hallVenue";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("hallVenue", hallVenue);
		
		List<Hall> hallVenueList=query.list();
		return hallVenueList;
	}

	@Override
	public Hall getSeatDetailsById(int hallId) {
		// TODO Auto-generated method stub
		String queryString="SELECT premiumSeat,goldSeat,silverSeat FROM "+Hall.class.getName()+" WHERE hallId = :hallId";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("hallId", hallId);
		
		List<Hall> idSeatList=query.list();
		return idSeatList.get(0);
	}

	@Override
	public List<Hall> getSeatDetailsByName(String hallName) {
		// TODO Auto-generated method stub
		String queryString="SELECT premiumSeat,goldSeat,silverSeat FROM "+Hall.class.getName()+" WHERE hallName LIKE :hallName";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("hallName", hallName);
		
		List<Hall> nameSeatList=query.list();
		return nameSeatList;
	}

}
