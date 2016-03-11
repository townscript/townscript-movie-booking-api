package com.dyulok.dewa.controller.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/")
@Controller
public class RootController {

	@RequestMapping(method=RequestMethod.GET,produces="text/html")
	public @ResponseBody String renderHtml(){
		return "<h1>Movie Ticketing Platform</h1>";
	}
}
