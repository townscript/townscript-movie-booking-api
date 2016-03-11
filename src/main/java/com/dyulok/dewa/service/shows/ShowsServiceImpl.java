package com.dyulok.dewa.service.shows;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.shows.ShowsDao;
import com.dyulok.dewa.model.shows.Show;

@Transactional
public class ShowsServiceImpl implements ShowsService {

	private static final Logger logger=Logger.getLogger(ShowsServiceImpl.class);
	
	@Autowired
	private ShowsDao showsDao;
	
	public Logger getLogger() {
		return logger;
	}

	public ShowsDao getShowsDao() {
		return showsDao;
	}

	public void setShowsDao(ShowsDao showsDao) {
		this.showsDao = showsDao;
	}

	@Override
	public int saveShow(Show show) {
		// TODO Auto-generated method stub
		int showId=showsDao.saveShow(show);
		logger.info("Show Added");
		return showId;
	}

	@Override
	public void deleteShow(int showId) {
		// TODO Auto-generated method stub
		showsDao.deleteShow(showId);
		logger.info("Show with ShowId:"+showId+" is deleted!!");
	}

	@Override
	public void updateShowToDate(int showId, Date toDate) {
		// TODO Auto-generated method stub
		Show shows=showsDao.searchShowById(showId);
		shows.setToDate(toDate);
		showsDao.updateShowToDate(shows);
	}

	@Override
	public void updateShowTime(int showId, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		Show shows=showsDao.searchShowById(showId);
		shows.setStartTime(startTime);
		shows.setEndTime(endTime);
		showsDao.updateShowPrice(shows);
	}

	@Override
	public void updateSeatPrice(int showId, float goldSeatPrice,
			float silverSeatPrice, float premiumSeatPrice) {
		// TODO Auto-generated method stub
		Show shows=showsDao.searchShowById(showId);
		shows.setPremiumPrice(premiumSeatPrice);
		shows.setGoldPrice(goldSeatPrice);
		shows.setSilverPrice(silverSeatPrice);
		showsDao.updateShowPrice(shows);
	}

	@Override
	public Show loadDetailsByShowId(int showId) {
		// TODO Auto-generated method stub
		return showsDao.searchShowById(showId);
	}

	@Override
	public List<Show> loadDetailsByHallName(String hallName) {
		// TODO Auto-generated method stub
		return showsDao.searchShowByHallName(hallName);
	}

	@Override
	public List<Show> loadDetailsByMovieName(String movieName) {
		// TODO Auto-generated method stub
		return showsDao.searchShowByMovieName(movieName);
	}

	@Override
	public Show loadDetailsByDate(Date date) {
		// TODO Auto-generated method stub
		return showsDao.searchShowByDate(date);
	}

	@Override
	public List<Show> loadDetailsByStartTime(Date startTime) {
		// TODO Auto-generated method stub
		return showsDao.searchShowByStartTime(startTime);
	}

}
