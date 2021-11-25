package com.klm.cases.controllers;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klm.cases.services.AirPortService;


@RestController
public class AirPortController {
	
	@Autowired
	private AirPortService airPortService;
	
	@GetMapping("/airports")
	    public ResponseEntity<String> list() {
		 try
		 {
			 Optional<String> seriveResultOptional = Optional.ofNullable(airPortService.getAirPortsData());
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
