package com.dyulok.dewa.dao.availability;

import java.util.Date;
import java.util.List;

import com.dyulok.dewa.model.availability.Availability;

public interface AvailabilityDao {

	/*Availability loadSeatAvailabilityById(int availId);
	Availability loadSeatAvailabilityByHallId(int hallId);
	Availability loadSeatAvailabilityByHallName(String hallName);
	Availability loadSeatAvailabilityByMovieId(int movieId);*/
	void updatePremiumSeat(Availability availability);
	void updateGoldSeat(Availability availability);
	void updateSilverSeat(Availability availability);
	Availability getGoldSeat(Date date,Date startTime);
	Availability getSilverSeat(Date date,Date startTime);
	Availability getPremiumSeat(Date date,Date startTime);
	int createEntryForAvailability(Availability availability);
	Availability loadSeatAvailabilityById(int availId);
	List<Availability> loadDetailsByDate(Date date);
	void deleteAvailabilityEntryByDate(Date date);
	void deleteAvailabilityEntryByid(int availId);
}
