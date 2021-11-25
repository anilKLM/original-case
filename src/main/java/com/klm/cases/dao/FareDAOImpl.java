package com.klm.cases.dao;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.klm.cases.utilities.CommonUtility;

@Repository
public class FareDAOImpl implements FareDAO{

	@Autowired
	private CommonUtility commonUtility;
	
	@Override
	public JSONObject getFareData(String urlString, String methodName, String origin, String destination, String user, String userPs) throws Exception {
		// TODO Auto-generated method stub
		return commonUtility.prepareConnection(urlString+origin+"/"+destination, "GET", user, userPs);
		
	}

}
