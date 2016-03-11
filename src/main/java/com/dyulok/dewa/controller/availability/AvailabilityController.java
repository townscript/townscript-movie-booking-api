package com.dyulok.dewa.controller.availability;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.availability.Availability;
import com.dyulok.dewa.service.availability.AvailabilityService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@RequestMapping(value="/availability")
@Controller
public class AvailabilityController {

	private AvailabilityService availabilityService;

	public AvailabilityService getAvailabilityService() {
		return availabilityService;
	}

	public void setAvailabilityService(AvailabilityService availabilityService) {
		this.availabilityService = availabilityService;
	}
	
	public AvailabilityController(){
		
		if(availabilityService==null){
			ApplicationContext context=new ClassPathXmlApplicationContext("/com/dyulok/dewa/mainbeans.xml");
			availabilityService=(AvailabilityService)context.getBean("availabilityServiceImpl");
		}
	};
	
	@RequestMapping(value="/createavailability", method=RequestMethod.POST)
	public @ResponseBody Availability createAvailability(@RequestParam(value="showId") int showId,@RequestParam(value="movieId") int movieId,@RequestParam(value="hallId") int hallId,@RequestParam(value="hallName") String hallName,@RequestParam(value="venue") String venue,@RequestParam(value="premiumSeat") int premiumSeat,@RequestParam(value="goldSeat") int goldSeat,@RequestParam(value="silverSeat") int silverSeat,@RequestParam(value="showDate") String showDate,@RequestParam(value="startTime") String startTime){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Availability availability=new Availability();
		availability.setShowId(showId);
		availability.setMovieId(movieId);
		availability.setHallId(hallId);
		availability.setHallName(hallName);
		availability.setVenue(venue);
		availability.setPremiumSeat(premiumSeat);
		availability.setGoldSeat(goldSeat);
		availability.setSilverSeat(silverSeat);
		Date mshowDate;
		try {
			mshowDate = sdfDate.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availability.setShowDate(mshowDate);
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availability.setStartTime(mstartTime);
		availabilityService.createAvailability(availability);
		return availability;
	}
	
	@RequestMapping(value="/deleteentrybyid", method=RequestMethod.GET)
	public @ResponseBody void deleteAvailabilityEntryById(@RequestParam(value="availabilityId") int availabilityId){
		availabilityService.deleteAvailabilityEntryById(availabilityId);
	}
	
	@RequestMapping(value="/deleteentrybydate", method=RequestMethod.GET)
	public @ResponseBody void deleteAvailabilityEntryByDate(@RequestParam(value="showDate") String showDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sdate;
		try {
			sdate = sdf.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availabilityService.deleteAvailabilityEntryByDate(sdate);
	}
	
	@RequestMapping(value="/availabilityinfobyid", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsById(@RequestParam(value="availabilityId") int availabilityId){
		Availability a=availabilityService.loadDetailsById(availabilityId);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		result.put("data", gson.toJson(a));
		return result;
	}
	
	@RequestMapping(value="/availabilityinfobydate", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByDate(@RequestParam(value="showDate") String showDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date sdate;
		try {
			sdate = sdf.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		List<Availability> a=availabilityService.loadDetailsByDate(sdate);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		result.put("data", gson.toJson(a));
		return result;
	}
	
	@RequestMapping(value="/buypremiumticket", method=RequestMethod.POST)
	public @ResponseBody String buyPremiumSeat(@RequestParam(value="showDate") String showDate,@RequestParam(value="startTime") String startTime,@RequestParam(value="noOfSeat") int noOfSeat){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Date mshowDate,mstartTime;
		try {
			mshowDate = sdfDate.parse(showDate);
			mstartTime=sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availabilityService.buyPremiumSeat(mshowDate, mstartTime, noOfSeat);
		return"Ticket Booked!!!!!";
	}
	
	@RequestMapping(value="/buygoldticket", method=RequestMethod.POST)
	public @ResponseBody String buyGoldSeat(@RequestParam(value="showDate") String showDate,@RequestParam(value="startTime") String startTime,@RequestParam(value="noOfSeat") int noOfSeat){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Date mshowDate;
		try {
			mshowDate = sdfDate.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availabilityService.buyGoldSeat(mshowDate, mstartTime, noOfSeat);
		return"Ticket Booked!!!!!";
	}
	
	@RequestMapping(value="/buysilverticket", method=RequestMethod.POST)
	public @ResponseBody String buySilverSeat(@RequestParam(value="showDate") String showDate,@RequestParam(value="startTime") String startTime,@RequestParam(value="noOfSeat") int noOfSeat){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Date mshowDate;
		try {
			mshowDate = sdfDate.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availabilityService.buySilverSeat(mshowDate, mstartTime, noOfSeat);
		return"Ticket Booked!!!!!";
	}
	
	@RequestMapping(value="/cancelpremiumticket", method=RequestMethod.POST)
	public @ResponseBody String cancelPremiumSeat(@RequestParam(value="showDate") String showDate,@RequestParam(value="startTime") String startTime,@RequestParam(value="noOfSeat") int noOfSeat){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Date mshowDate;
		try {
			mshowDate = sdfDate.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availabilityService.cancelPremiumSeat(mshowDate, mstartTime, noOfSeat);
		return"Ticket Canceled";
	}
	
	@RequestMapping(value="/cancelgoldticket", method=RequestMethod.POST)
	public @ResponseBody String cancelGoldSeat(@RequestParam(value="showDate") String showDate,@RequestParam(value="startTime") String startTime,@RequestParam(value="noOfSeat") int noOfSeat){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Date mshowDate;
		try {
			mshowDate = sdfDate.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availabilityService.cancelGoldSeat(mshowDate, mstartTime, noOfSeat);
		return"Ticket Canceled";
	}
	
	@RequestMapping(value="/cancelsilverticket", method=RequestMethod.POST)
	public @ResponseBody String cancelSilverSeat(@RequestParam(value="showDate") String showDate,@RequestParam(value="startTime") String startTime,@RequestParam(value="noOfSeat") int noOfSeat){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Date mshowDate;
		try {
			mshowDate = sdfDate.parse(showDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		availabilityService.cancelSilverSeat(mshowDate, mstartTime, noOfSeat);
		return"Ticket Canceled";
	}
}
