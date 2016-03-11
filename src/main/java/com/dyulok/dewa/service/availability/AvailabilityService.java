package com.dyulok.dewa.service.availability;

import java.util.Date;
import java.util.List;

import com.dyulok.dewa.model.availability.Availability;

//enum seatType{Premium,Gold,Silver}

public interface AvailabilityService {

	Availability loadDetailsById(int availId);
	List<Availability> loadDetailsByDate(Date date);
	void buyPremiumSeat(Date date,Date startTime,int noOfSeat);
	void cancelPremiumSeat(Date date,Date startTime,int noOfSeat);
	void buyGoldSeat(Date date,Date startTime,int noOfSeat);
	void cancelGoldSeat(Date date,Date startTime,int noOfSeat);
	void buySilverSeat(Date date,Date startTime,int noOfSeat);
	void cancelSilverSeat(Date date,Date startTime,int noOfSeat);
	int createAvailability(Availability availability);
	void deleteAvailabilityEntryByDate(Date date);
	void deleteAvailabilityEntryById(int availId);
	/*void buyTicket(Date date,int noOfSeat);
	void cancelTicket(Date date,int noOfSeat);*/
}
