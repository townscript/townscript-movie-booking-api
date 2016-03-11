package com.dyulok.dewa.controller.shows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.shows.Show;
import com.dyulok.dewa.service.shows.ShowsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequestMapping(value="/shows")
@Controller
public class ShowsController {

	private ShowsService showsService;

	public ShowsService getShowsService() {
		return showsService;
	}

	public void setShowsService(ShowsService showsService) {
		this.showsService = showsService;
	}
	
	public ShowsController(){
		
		if(showsService==null){
			ApplicationContext context=new ClassPathXmlApplicationContext("/com/dyulok/dewa/mainbeans.xml");
			showsService=(ShowsService)context.getBean("showsServiceImpl");
		}
	}
	
	@RequestMapping(value="/saveshow", method=RequestMethod.POST)
	public @ResponseBody Show saveShows(@RequestParam(value="hallId") int hallId,@RequestParam(value="hallName") String hallName,@RequestParam(value="movieId") int movieId,@RequestParam(value="movieName") String movieName,@RequestParam(value="startTime") String startTime,@RequestParam(value="endTime") String endTime,@RequestParam(value="fromDate") String fromDate,@RequestParam(value="toDate") String toDate,@RequestParam(value="premiumPrice") float premiumPrice,@RequestParam(value="goldPrice") float goldPrice,@RequestParam(value="silverPrice") float silverPrice){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		
		Show show=new Show();
		show.setHallId(hallId);
		show.setHallName(hallName);
		show.setMovieId(movieId);
		show.setMovieName(movieName);
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		show.setStartTime(mstartTime);
		Date mendTime;
		try {
			mendTime = sdfTime.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		show.setEndTime(mendTime);
		Date mfromDate;
		try {
			mfromDate = sdfDate.parse(fromDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		show.setFromDate(mfromDate);
		Date mtoDate;
		try {
			mtoDate = sdfDate.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		show.setToDate(mtoDate);
		show.setPremiumPrice(premiumPrice);
		show.setGoldPrice(goldPrice);
		show.setSilverPrice(silverPrice);
		showsService.saveShow(show);
		return show;
	}
	
	@RequestMapping(value="/deleteshow", method=RequestMethod.GET)
	public @ResponseBody String deleteShow(@RequestParam(value="showId") int showId){
		showsService.deleteShow(showId);
		return"Show Deleted!!!!";
	}
	
	@RequestMapping(value="/updatetodate", method=RequestMethod.POST)
	public @ResponseBody String updateShowToDate(@RequestParam(value="showId") int showId,@RequestParam(value="toDate") String toDate){
		
		SimpleDateFormat sdfDate=new SimpleDateFormat("yyyy-MM-dd");
		Date mtoDate;
		try {
			mtoDate = sdfDate.parse(toDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		showsService.updateShowToDate(showId, mtoDate);
		return"Date Updated!!!";
	}
	
	@RequestMapping(value="/updateshowtime", method=RequestMethod.POST)
	public @ResponseBody String updateShowTime(@RequestParam(value="showId") int showId,@RequestParam(value="startTime") String startTime,@RequestParam(value="endTime") String endTime){
		
		SimpleDateFormat sdfTime=new SimpleDateFormat("HH:mm:ss");
		Date mstartTime;
		try {
			mstartTime = sdfTime.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Date mendTime;
		try {
			mendTime = sdfTime.parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		showsService.updateShowTime(showId, mstartTime, mendTime);
		return "Timing Updated!!!";
	}
	
	@RequestMapping(value="/updateseatprice", method=RequestMethod.POST)
	public @ResponseBody String updateSeatPrice(@RequestParam(value="showId") int showId,@RequestParam(value="premiumPrice") float premiumPrice,@RequestParam(value="goldPrice") float goldPrice,@RequestParam(value="silverPrice") float silverPrice){
		showsService.updateSeatPrice(showId, goldPrice, silverPrice, premiumPrice);
		return"Seat Price Updated!!!!";
	}
	
	@RequestMapping(value="/detailsbyshowid", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByShowId(@RequestParam(value="showId") int showId){
		Show s=showsService.loadDetailsByShowId(showId);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		result.put("date", gson.toJson(s));
		return result;
	}
	
	@RequestMapping(value="/detailsbyhallname", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByHallName(@RequestParam(value="hallName") String hallName){
		List<Show> s=showsService.loadDetailsByHallName(hallName);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		result.put("data", gson.toJson(s));
		return result;
	}
	
	@RequestMapping(value="/detailsbymoviename", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByMovieName(@RequestParam(value="movieName") String movieName){
		List<Show> s = showsService.loadDetailsByMovieName(movieName);
		JSONObject result = new JSONObject();
		result.put("result", "success");
		Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		result.put("data", gson.toJson(s));
		return result;
	}
	
	@RequestMapping(value="/detailsbystarttime", method=RequestMethod.GET)
	public @ResponseBody JSONObject loadDetailsByDate(@RequestParam(value="startTime") String startTime){
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
		Date stime;
		try {
			stime = sdf.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		List<Show> s=showsService.loadDetailsByStartTime(stime);
		JSONObject result=new JSONObject();
		result.put("result", "success");
		Gson gson=new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
		result.put("data", gson.toJson(s));
		return result;
	}
}
