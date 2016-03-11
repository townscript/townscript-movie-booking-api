package com.dyulok.dewa.service.hall;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.hall.HallDao;
import com.dyulok.dewa.dao.hall.HallDaoImpl;
import com.dyulok.dewa.model.hall.Hall;
import com.dyulok.dewa.service.customer.CustomerServiceImpl;

@Transactional
public class HallServiceImpl implements HallService {

	private static final Logger logger=Logger.getLogger(HallServiceImpl.class);
	
	@Autowired
	HallDao hallDao;
	
	public static Logger getLogger() {
		return logger;
	}

	public HallDao getHallDao() {
		return hallDao;
	}

	public void setHallDao(HallDao hallDao) {
		this.hallDao = hallDao;
	}

	@Override
	public int saveHall(Hall hall) {
		// TODO Auto-generated method stub
		int hallId=hallDao.addHall(hall);
		logger.info("Hall Added!!!!");
		return hallId;
	}

	@Override
	public void updateHallName(int hallId,String hallName) {
		// TODO Auto-generated method stub
		Hall hall=hallDao.loadHallbyid(hallId);
		hall.setHallName(hallName);
		hallDao.updateHall(hall);
		logger.info("Hall Name with HALL_ID="+hallId+" is Updated!!!");
	}

	@Override
	public Hall loadHallInfoById(int hallId) {
		// TODO Auto-generated method stub
		return hallDao.loadHallbyid(hallId);
	}

	@Override
	public List<Hall> loadHallInfoByName(String hallName) {
		// TODO Auto-generated method stub
		return hallDao.loadHallbyName(hallName);
	}

	@Override
	public List<Hall> loadHallInfoByVenue(String hallVenue) {
		// TODO Auto-generated method stub
		return hallDao.loadHallbyVenue(hallVenue);
	}

	@Override
	public Hall getSeatDetailsById(int hallId) {
		// TODO Auto-generated method stub
		return hallDao.getSeatDetailsById(hallId);
	}

	@Override
	public List<Hall> getSeatDetailsByName(String hallName) {
		// TODO Auto-generated method stub
		return hallDao.getSeatDetailsByName(hallName);
	}

	@Override
	public void deleteHall(int hallId) {
		// TODO Auto-generated method stub
		hallDao.deleteHall(hallId);
		Logger logger= Logger.getLogger(HallServiceImpl.class);
		logger.info("Hall record with id "+hallId+" is deleted!!!!");
	}

}
