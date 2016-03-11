package com.dyulok.dewa.dao.shows;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dyulok.dewa.model.shows.Show;

public class ShowsDaoHnateImpl extends HibernateDaoSupport implements ShowsDao {

	@Override
	public int saveShow(Show shows) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(shows);
		int showId=shows.getShowId();
		return showId;
	}

	@Override
	public void deleteShow(int showId) {
		// TODO Auto-generated method stub
		Show shows=new Show();
		shows.setShowId(showId);
		getHibernateTemplate().delete(shows);
	}

	@Override
	public void updateShow(Show show) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(show);
	}

	@Override
	public void updateShowPrice(Show show) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(show);
	}

	@Override
	public void updateShowToDate(Show show) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(show);
	}

	@Override
	public void updateShowTime(Show show) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(show);
	}

	@Override
	public Show searchShowById(int showId) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Show.class.getName()+" WHERE showId = :showId";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("showId", showId);
		
		List<Show> listShowsById=query.list();
		return listShowsById.get(0);
	}

	@Override
	public List<Show> searchShowByHallName(String hallName) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Show.class.getName()+" WHERE hallName = :hallName";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("hallName",hallName);
		
		List<Show> listShowByHallName=query.list();
		return listShowByHallName;
	}

	@Override
	public List<Show> searchShowByMovieName(String movieName) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Show.class.getName()+" WHERE movieName LIKE :movieName";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("movieName", movieName);
		
		List<Show> listShowByMovieName=query.list();
		return listShowByMovieName;
	}

	@Override
	public List<Show> searchShowByStartTime(Date startTime) {
		// TODO Auto-generated method stub
		String querString="FROM "+Show.class.getName()+" WHERE startTime = :startTime";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(querString);
		query.setParameter("startTime", startTime);
		
		List<Show> listShowByStartTime=query.list();
		return listShowByStartTime;
	}

	@Override
	public Show searchShowByDate(Date date) {
		// TODO Auto-generated method stub
		String queryString="FROM "+Show.class.getName()+" WHERE fromDate<=:date AND toDate>=:date";
		
		Session session=getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query=session.createQuery(queryString);
		query.setParameter("date", date);
		/*Criteria cr=session.createCriteria(Shows.class);
		cr.add(Restrictions.between(date, 2016-02-21, 2016-02-28));*/
		
		List<Show> listShowByDate=query.list();
		return listShowByDate.get(0);
	}
}