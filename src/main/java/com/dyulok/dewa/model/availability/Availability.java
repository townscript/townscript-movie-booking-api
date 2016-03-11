package com.dyulok.dewa.model.availability;

import java.util.Date;

//import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="AVAILABILITY")
public class Availability {

	@Id
	@GeneratedValue
	@Column(name="AVAILABILITY_ID")
	private int availabilityId;
	
	@Column(name="MOVIE_ID")
	private int movieId;
	
	@Column(name="HALL_ID")
	private int hallId;
	
	@Column(name="SHOW_ID")
	private int showId;
	
	@Column(name="PREMIUM_SEAT")
	private int premiumSeat;
	
	@Column(name="GOLD_SEAT")
	private int goldSeat;
	
	@Column(name="SILVER_SEAT")
	private int silverSeat;
	
	@Column(name="HALL_NAME")
	private String hallName;
	
	@Column(name="VENUE")
	private String venue;
	
	@Column(name="START_TIME")
	private Date startTime;
	
	@Column(name="SHOW_DATE")
	private Date showDate;
	
	public int getAvailabilityId() {
		return availabilityId;
	}
	public void setAvailabilityId(int availabilityId) {
		this.availabilityId = availabilityId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getPremiumSeat() {
		return premiumSeat;
	}
	public void setPremiumSeat(int premiumSeat) {
		this.premiumSeat = premiumSeat;
	}
	public int getGoldSeat() {
		return goldSeat;
	}
	public void setGoldSeat(int goldSeat) {
		this.goldSeat = goldSeat;
	}
	public int getSilverSeat() {
		return silverSeat;
	}
	public void setSilverSeat(int silverSeat) {
		this.silverSeat = silverSeat;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
}