package com.coll.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController 
{
	@GetMapping(value="/demo")
	public String testRestful()
	{
		return "Restful Service Working";
	}
}
