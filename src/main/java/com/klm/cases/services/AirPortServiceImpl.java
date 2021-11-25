package com.klm.cases.services;


import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.klm.cases.dao.AirPortDAO;

@Service
public class AirPortServiceImpl implements AirPortService {
	
	@Autowired
	private AirPortDAO airPortDAO;
	
	
	@Value("${aiports.endpoint}")
	private String urlString;
	@Value("${endpoint.username}")
	private String username;
	@Value("${userpass}")
	private String userpass;

	@Override
	public String getAirPortsData() throws Exception {
		// TODO Auto-generated method stub
		Optional<JSONObject> resultOptional = Optional.ofNullable(airPortDAO.getAirPortsData(urlString, "GET", username, userpass)) ;
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
