package com.dyulok.dewa.dao.shows;

import java.util.Date;
import java.util.List;

import com.dyulok.dewa.model.shows.Show;

public interface ShowsDao {

	int saveShow(Show shows);
	void deleteShow(int showId);
	void updateShow(Show show);
	void updateShowPrice(Show show);
	void updateShowToDate(Show show);
	void updateShowTime(Show show);
	Show searchShowById(int showId);
	List<Show> searchShowByHallName(String hallName);
	List<Show> searchShowByMovieName(String movieName);
	List<Show> searchShowByStartTime(Date startTime);
	Show searchShowByDate(Date date);
}