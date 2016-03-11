package com.dyulok.dewa.controller.hall;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.hall.Hall;
import com.dyulok.dewa.service.hall.HallService;

@RequestMapping(value="/hall")
@Controller
public class HallController {

	public HallService hallService;

	public HallService getHallService() {
		return hallService;
	}

	public void setHallService(HallService hallService) {
		this.hallService = hallService;
	}
	
	public HallController(){
		
		if(hallService==null){
			ApplicationContext context=new ClassPathXmlApplicationContext("/com/dyulok/dewa/mainbeans.xml");
			hallService=(HallService)context.getBean("hallServiceImpl");
		}
	};
	
	@RequestMapping(value="/savehall", method=RequestMethod.POST)
	public @ResponseBody Hall saveHall(@RequestParam(value="hallName") String hallName,@RequestParam(value="venue") String venue,@RequestParam(value="premiumSeat") int premiumSeat,@RequestParam(value="goldSeat") int goldSeat,@RequestParam(value="silverSeat") int silverSeat){
		
		Hall hall=new Hall();
		hall.setHallName(hallName);
		hall.setVenue(venue);
		hall.setPremiumSeat(premiumSeat);
		hall.setGoldSeat(goldSeat);
		hall.setSilverSeat(silverSeat);
		hallService.saveHall(hall);
		return hall;
	}
	
	@RequestMapping(value="/updatehallname", method=RequestMethod.POST)
	public @ResponseBody void updateHallName(@RequestParam(value="hallId") int hallId,@RequestParam(value="hallName") String hallName){
		hallService.updateHallName(hallId, hallName);
	}
	
	@RequestMapping(value="/hallinfobyid", method=RequestMethod.GET)
	public @ResponseBody Hall loadHallInfoById(@RequestParam(value="hallId") int hallId){
		return hallService.loadHallInfoById(hallId);
	}
	
	@RequestMapping(value="/hallinfobyname", method=RequestMethod.GET)
	public @ResponseBody List<Hall> loadHallInfoByName(@RequestParam(value="hallName") String hallName){
		return hallService.loadHallInfoByName(hallName);
	}
	
	@RequestMapping(value="/hallinfobyvenue", method=RequestMethod.GET)
	public @ResponseBody List<Hall> loadHallInfoByVenue(@RequestParam(value="venue") String venue){
		return hallService.loadHallInfoByVenue(venue);
	}
	
	@RequestMapping(value="/deletehall", method=RequestMethod.GET)
	public @ResponseBody void deleteHall(@RequestParam(value="hallId") int hallId){
		hallService.deleteHall(hallId);
	}
	
	@RequestMapping(value="/seatinfobyhallid", method=RequestMethod.GET)
	public @ResponseBody Hall getSeatDetailsById(@RequestParam(value="hallId") int hallId){
		return hallService.getSeatDetailsById(hallId);
	}
	
	@RequestMapping(value="/seatinfobyhallname", method=RequestMethod.GET)
	public @ResponseBody List<Hall> getSeatDetailsByName(@RequestParam(value="hallName") String hallName){
		return hallService.getSeatDetailsByName(hallName);
	}
}
