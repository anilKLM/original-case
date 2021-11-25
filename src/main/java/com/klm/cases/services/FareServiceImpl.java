package com.klm.cases.services;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.klm.cases.dao.FareDAO;


@Service
public class FareServiceImpl implements FareService{

	@Autowired
	private FareDAO fareDAO;
	
	@Value("${fares.endpoint}")
	private String urlString;
	@Value("${endpoint.username}")
	private String username;
	@Value("${userpass}")
	private String userpass;
	
	
	@Override
	public String getFareData(String origin, String destination) throws Exception {
		// TODO Auto-generated method stub
		Optional<JSONObject> resultOptional = Optional.ofNullable(fareDAO.getFareData(urlString, "GET", origin, destination, username, userpass)) ;
		if(resultOptional.isPresent())
		{
			JSONObject resultJson = resultOptional.get();
			int responseCode = resultJson.getInt("responseCode");
			if(responseCode >= 200 && responseCode <= 299)
				return resultJson.toString();
			return null;
		}else
		{
			return null;
		}
	}

}
