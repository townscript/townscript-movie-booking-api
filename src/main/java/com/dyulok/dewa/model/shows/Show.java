package com.dyulok.dewa.model.shows;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import com.dyulok.dewa.model.hall.Hall;
//import com.dyulok.dewa.model.movie.Movie;

@Entity
@Table(name="SHOWS")
public class Show {

	@Id
	@GeneratedValue
	@Column(name="SHOW_ID")
	private int showId;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@Join*/@Column(name="HALL_ID")
	private int hallId;
	
	/*@ManyToOne(fetch=FetchType.LAZY)
	@Join*/@Column(name="MOVIE_ID")
	private int movieId;
	
	@Column(name="PREMIUM_SEAT_PRICE")
	private float premiumPrice;
	
	@Column(name="GOLD_SEAT_PRICE")
	private float goldPrice;
	
	@Column(name="SILVER_SEAT_PRICE")
	private float silverPrice;
	
	@Column(name="START_TIME")
	private Date startTime;
	
	@Column(name="END_TIME")
	private Date endTime;
	
	@Column(name="FROM_DATE")
	private Date fromDate;
	
	@Column(name="TO_DATE")
	private Date toDate;
	
	@Column(name="MOVIE_NAME")
	private String movieName;
	
	@Column(name="HALL_NAME")
	private String hallName;
	
	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	public int getHallId() {
		return hallId;
	}
	public void setHallId(int hallId) {
		this.hallId = hallId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public float getPremiumPrice() {
		return premiumPrice;
	}
	public void setPremiumPrice(float premiumPrice) {
		this.premiumPrice = premiumPrice;
	}
	public float getGoldPrice() {
		return goldPrice;
	}
	public void setGoldPrice(float goldPrice) {
		this.goldPrice = goldPrice;
	}
	public float getSilverPrice() {
		return silverPrice;
	}
	public void setSilverPrice(float silverPrice) {
		this.silverPrice = silverPrice;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getHallName() {
		return hallName;
	}
	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
}