package com.klm.cases.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klm.cases.services.FareService;

@RestController
public class FareController {
	

	
	@Autowired
	private FareService fareService;
	
	@GetMapping("/fares")
	    public ResponseEntity<String> getFares(@RequestParam final String origin, @RequestParam final String destination) {
		 try
		 {
			 Optional<String> seriveResultOptional = Optional.ofNullable(fareService.getFareData(origin, destination));
			 if(seriveResultOptional.isPresent())
				 return new ResponseEntity<String>(seriveResultOptional.get(), HttpStatus.OK);
			 return new ResponseEntity<String>(HttpStatus.BAD_REQUEST); 
			
		 }
	      catch(Exception e)
		 {
	    	  return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		 }
	    }



}
