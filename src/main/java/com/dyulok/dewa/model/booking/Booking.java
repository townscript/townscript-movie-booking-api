package com.dyulok.dewa.model.booking;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BOOKING")
public class Booking {

	@Id
	@GeneratedValue
	@Column(name="BOOKING_ID")
	private int bookingId;
	
	@Column(name="CUSTOMER_ID")
	private int customerId;
	
	@Column(name="MOVIE_ID")
	private int movieId;
	
	@Column(name="HALL_ID")
	private int hallId;
	
	@Column(name="SHOW_ID")
	private int showId;
	
	@Column(name="NO_OF_SEATS")
	private int noOfSeat;
	
	@Column(name="SEAT_PRICE")
	private float price;
	
	@Column(name="TOTAL_COST")
	private float totalCost;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="MOVIE_NAME")
	private String movieName;
	
	@Column(name="HALL_NAME")
	private String hallName;
	
	@Column(name="SEAT_TYPE")
	private String seatType;
	
	@Column(name="VENUE")
	private String venue;
	
	@Column(name="CONTACT")
	private String contact;
	
	@Column(name="BOOKING_DATE")
	private Date bookingDate;
	
	@Column(name="START_TIME")
	private Date startTime;
	
	@Column(name="END_TIME")
	private Date endTime;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getVenue() {
		return venue;
	}
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}


	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
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
	
		
}
