package com.dyulok.dewa.dao.booking;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dyulok.dewa.model.booking.Booking;

public class BookingDaoHnateImpl extends HibernateDaoSupport implements BookingDao {

	@Override
	public int createEntryForBooking(Booking booking) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(booking);
		int bookid=booking.getBookingId();
		return bookid;
	}

	@Override
	public void deleteBookingById(int bookid) {
		// TODO Auto-generated method stub
		Booking booking=new Booking();
		booking.setBookingId(bookid);
		getHibernateTemplate().delete(booking);
	}

	@Override
	public Booking loadDetailsByBookingId(int bookid) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Booking.class.getName()+" WHERE BOOKING_ID = :bookid";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("bookid", bookid);
		
		List<Booking> listById=query.list();
		return listById.get(0);
	}

	@Override
	public List<Booking> loadDetailsByCustomerId(int custid) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Booking.class.getName()+" WHERE CUSTOMER_ID = :custid";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("custid", custid);
		
		List<Booking> listByCustId=query.list();
		return listByCustId;
	}

	@Override
	public List<Booking> loadDeatilsByBookingDate(Date date) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Booking.class.getName()+" WHERE BOOKING_DATE = :date";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("date", date);
		
		List<Booking> listByDate=query.list();
		return listByDate;
	}

}
