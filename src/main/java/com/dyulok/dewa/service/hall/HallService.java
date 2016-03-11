package com.dyulok.dewa.service.hall;

import java.util.List;

import com.dyulok.dewa.model.hall.Hall;

public interface HallService {

	int saveHall(Hall hall);
	void updateHallName(int hallId,String hallName);
	Hall loadHallInfoById(int hallId);
	List<Hall> loadHallInfoByName(String hallName);
	List<Hall> loadHallInfoByVenue(String hallVenue);
	Hall getSeatDetailsById(int hallId);
	List<Hall> getSeatDetailsByName(String hallName);
	void deleteHall(int hallId);
}
