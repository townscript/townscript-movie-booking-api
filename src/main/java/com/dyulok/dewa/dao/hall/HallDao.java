package com.dyulok.dewa.dao.hall;

import java.util.List;

import com.dyulok.dewa.model.hall.Hall;

public interface HallDao {

	int addHall(Hall hall);
	void deleteHall(int hallId);
	void updateHall(Hall hall);
	Hall loadHallbyid(int hallId);
	List<Hall> loadHallbyName(String hallName);
	List<Hall> loadHallbyVenue(String hallVenue);
	Hall getSeatDetailsById(int hallId);
	List<Hall> getSeatDetailsByName(String hallName);
}
