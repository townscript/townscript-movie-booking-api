package com.dyulok.dewa.service.shows;

import java.util.Date;
import java.util.List;

import com.dyulok.dewa.model.shows.Show;

public interface ShowsService {

	int saveShow(Show show);
	void deleteShow(int showId);
	void updateShowToDate(int showId,Date toDate);
	void updateShowTime(int showId,Date startTime,Date endTime);
	void updateSeatPrice(int showId,float goldSeatPrice,float silverSeatPrice,float premiumSeatPrice);
	Show loadDetailsByShowId(int showId);
	List<Show> loadDetailsByHallName(String hallName);
	List<Show> loadDetailsByMovieName(String movieName);
	Show loadDetailsByDate(Date date);
	List<Show> loadDetailsByStartTime(Date startTime);
}
