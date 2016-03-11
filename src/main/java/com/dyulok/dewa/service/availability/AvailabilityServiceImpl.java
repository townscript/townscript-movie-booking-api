package com.dyulok.dewa.service.availability;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dyulok.dewa.dao.availability.AvailabilityDao;
import com.dyulok.dewa.model.availability.Availability;

@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {

	private static final Logger logger=Logger.getLogger(AvailabilityServiceImpl.class);
	
	@Autowired
	private AvailabilityDao availabilityDao;
	
	public static Logger getLogger() {
		return logger;
	}

	public AvailabilityDao getAvailabilityDao() {
		return availabilityDao;
	}

	public void setAvailabilityDao(AvailabilityDao availabilityDao) {
		this.availabilityDao = availabilityDao;
	}

	@Override
	public void buyPremiumSeat(Date date,Date startTime,int noOfSeat) {
		// TODO Auto-generated method stub
		Availability availability=availabilityDao.getPremiumSeat(date, startTime);
		int actualNoOfSeat=availability.getPremiumSeat();
		actualNoOfSeat=actualNoOfSeat-noOfSeat;
		availability.setPremiumSeat(actualNoOfSeat);
		availabilityDao.updatePremiumSeat(availability);
	}

	@Override
	public void cancelPremiumSeat(Date date,Date startTime, int noOfSeat) {
		// TODO Auto-generated method stub
		Availability availability=availabilityDao.getPremiumSeat(date, startTime);
		int actualNoOfSeat=availability.getPremiumSeat();
		actualNoOfSeat=actualNoOfSeat+noOfSeat;
		availability.setPremiumSeat(actualNoOfSeat);
		availabilityDao.updatePremiumSeat(availability);
	}

	@Override
	public void buyGoldSeat(Date date,Date startTime, int noOfSeat) {
		// TODO Auto-generated method stub
		Availability availability=availabilityDao.getGoldSeat(date, startTime);
		int actualNoOfSeat=availability.getGoldSeat();
		actualNoOfSeat=actualNoOfSeat-noOfSeat;
		availability.setGoldSeat(actualNoOfSeat);
		availabilityDao.updateGoldSeat(availability);
	}

	@Override
	public void cancelGoldSeat(Date date,Date startTime, int noOfSeat) {
		// TODO Auto-generated method stub
		Availability availability=availabilityDao.getGoldSeat(date, startTime);
		int actualNoOfSeat=availability.getGoldSeat();
		actualNoOfSeat=actualNoOfSeat+noOfSeat;
		availability.setGoldSeat(actualNoOfSeat);
		availabilityDao.updateGoldSeat(availability);
	}

	@Override
	public void buySilverSeat(Date date,Date startTime, int noOfSeat) {
		// TODO Auto-generated method stub
		Availability availability=availabilityDao.getSilverSeat(date, startTime);
		int actualNoOfSeat=availability.getSilverSeat();
		actualNoOfSeat=actualNoOfSeat-noOfSeat;
		availability.setSilverSeat(actualNoOfSeat);
		availabilityDao.updateSilverSeat(availability);
	}

	@Override
	public void cancelSilverSeat(Date date,Date startTime, int noOfSeat) {
		// TODO Auto-generated method stub
		Availability availability=availabilityDao.getSilverSeat(date, startTime);
		int actualNoOfSeat=availability.getSilverSeat();
		actualNoOfSeat=actualNoOfSeat+noOfSeat;
		availability.setSilverSeat(actualNoOfSeat);
		availabilityDao.updateSilverSeat(availability);
	}

	@Override
	public int createAvailability(Availability availability) {
		// TODO Auto-generated method stub
		int availId=availabilityDao.createEntryForAvailability(availability);
		logger.info("Entry Generated!!!");
		return availId;
	}

	@Override
	public Availability loadDetailsById(int availId) {
		// TODO Auto-generated method stub
		return availabilityDao.loadSeatAvailabilityById(availId);
	}

	@Override
	public List<Availability> loadDetailsByDate(Date date) {
		// TODO Auto-generated method stub
		return availabilityDao.loadDetailsByDate(date);
	}

	@Override
	public void deleteAvailabilityEntryByDate(Date date) {
		// TODO Auto-generated method stub
		availabilityDao.deleteAvailabilityEntryByDate(date);
	}

	@Override
	public void deleteAvailabilityEntryById(int availId) {
		// TODO Auto-generated method stub
		availabilityDao.deleteAvailabilityEntryByid(availId);
	}

}
