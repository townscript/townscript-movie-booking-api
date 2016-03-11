package com.dyulok.dewa.model.movie;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MOVIE")
public class Movie {

	@Id
	@GeneratedValue
	@Column(name="MOVIE_ID")
	private int movieId;
	
	@Column(name="DURATION")
	private int duration;
	
	@Column(name="MOVIE_NAME")
	private String movieName;
	
	@Column(name="LANGUAGE")
	private String language;
	
	@Column(name="GENERE")
	private String genere;
	
	@Column(name="TYPE")
	private String type;
	
	/*@OneToMany(fetch=FetchType.LAZY,mappedBy="MOVIE")
	private List<Movie> movie;*/
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/*public List<Movie> getMovie() {
		return movie;
	}
	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}*/
	
}
