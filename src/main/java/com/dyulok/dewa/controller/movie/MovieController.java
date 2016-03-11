package com.dyulok.dewa.controller.movie;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dyulok.dewa.model.movie.Movie;
import com.dyulok.dewa.service.movie.MovieService;

@RequestMapping("/movie")
@Controller
public class MovieController {

	private MovieService movieService;

	public MovieService getMovieService() {
		return movieService;
	}

	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
	
	public MovieController(){
		
		if(movieService==null){
			ApplicationContext context=new ClassPathXmlApplicationContext("/com/dyulok/dewa/mainbeans.xml");
			movieService=(MovieService)context.getBean("movieServiceImpl");
		}
	};
	
	@RequestMapping(value="/savemovie", method=RequestMethod.POST)
	public @ResponseBody Movie saveMovie(@RequestParam(value="movieName") String movieName,@RequestParam(value="language") String language,@RequestParam(value="genere") String genere,@RequestParam(value="type") String type,@RequestParam(value="duration") int duration){
		
		Movie movie=new Movie();
		movie.setMovieName(movieName);
		movie.setLanguage(language);
		movie.setGenere(genere);
		movie.setType(type);
		movie.setDuration(duration);
		movieService.saveMovie(movie);
		return movie;
	}
	
	@RequestMapping(value="/movieinfobyid", method=RequestMethod.GET)
	public @ResponseBody Movie getMInfobyid(@RequestParam(value="movieId") int movieId){
		return movieService.getMInfobyid(movieId);
	}
	
	@RequestMapping(value="/movieinfobyname", method=RequestMethod.GET)
	public @ResponseBody Movie getMInfobyname(@RequestParam(value="movieName") String movieName){
		return movieService.getMInfobyname(movieName);
	}
	
	@RequestMapping(value="/movieinfobygenere", method=RequestMethod.GET)
	public @ResponseBody List<Movie> getMInfobygenere(@RequestParam(value="genere") String genere){
		return movieService.getMInfobygenere(genere);
	}
	
	@RequestMapping(value="/movieinfobylanguage", method=RequestMethod.GET)
	public @ResponseBody List<Movie> getMInfobylanguage(@RequestParam(value="language") String language){
		return movieService.getMInfobylanguage(language);
	}
	
	@RequestMapping(value="/deletemovie", method=RequestMethod.GET)
	public @ResponseBody void deleteMovie(@RequestParam(value="movieId") int movieId){
		movieService.deleteMovie(movieId);
	} 
}
