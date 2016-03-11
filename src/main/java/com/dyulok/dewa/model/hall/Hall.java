package com.dyulok.dewa.model.hall;

//import java.util.Set;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

//import com.dyulok.dewa.model.shows.Shows;

@Entity
@Table(name="HALL")
public class Hall {

	@Id
	@GeneratedValue
	@Column(name="HALL_ID")
	private int hallId;
	
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
	
	
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
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
}
