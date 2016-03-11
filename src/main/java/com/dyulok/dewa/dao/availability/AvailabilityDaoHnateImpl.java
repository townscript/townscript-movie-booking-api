package com.dyulok.dewa.dao.availability;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dyulok.dewa.model.availability.Availability;

public class AvailabilityDaoHnateImpl extends HibernateDaoSupport implements AvailabilityDao {

	@Override
	public Availability loadSeatAvailabilityById(int availId) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Availability.class.getName()+" WHERE availabilityId = :availId";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("availId", availId);
		
		List<Availability> listAvailById=query.list();
		return listAvailById.get(0);
	}

	/*@Override
	public Availability loadSeatAvailabilityByHallId(int hallId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Availability loadSeatAvailabilityByHallName(String hallName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Availability loadSeatAvailabilityByMovieId(int movieId) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void updatePremiumSeat(Availability availability) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(availability);
	}

	@Override
	public void updateGoldSeat(Availability availability) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(availability);
	}

	@Override
	public void updateSilverSeat(Availability availability) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(availability);
	}

	@Override
	public Availability getGoldSeat(Date date,Date startTime) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Availability.class.getName()+" WHERE showDate = :date AND startTime = :startTime";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("date", date);
		query.setParameter("startTime", startTime);
		
		List<Availability> noOfSeat=query.list();
		return noOfSeat.get(0);
	}

	@Override
	public Availability getSilverSeat(Date date,Date startTime) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Availability.class.getName()+" WHERE showDate = :date AND startTime = :startTime";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("date", date);
		query.setParameter("startTime", startTime);
		
		List<Availability> noOfSeat=query.list();
		return noOfSeat.get(0);
	}

	@Override
	public Availability getPremiumSeat(Date date,Date startTime) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Availability.class.getName()+" WHERE showDate = :date AND startTime = :startTime";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("date", date);
		query.setParameter("startTime",startTime);
		
		List<Availability> noOfSeat=query.list();
		return noOfSeat.get(0);
	}

	@Override
	public int createEntryForAvailability(Availability availability) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(availability);
		int availId=availability.getAvailabilityId();
		return availId;
	}

	@Override
	public List<Availability> loadDetailsByDate(Date date) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Availability.class.getName()+" WHERE showDate = :date";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("date", date);
		
		List<Availability> listByDate=query.list();
		return listByDate;
	}

	@Override
	public void deleteAvailabilityEntryByDate(Date date) {
		// TODO Auto-generated method stub
		Availability avail=new Availability();
		avail.setShowDate(date);
		getHibernateTemplate().delete(avail);
	}

	@Override
	public void deleteAvailabilityEntryByid(int availId) {
		// TODO Auto-generated method stub
		Availability avail=new Availability();
		avail.setAvailabilityId(availId);
		getHibernateTemplate().delete(avail);
	}

}
