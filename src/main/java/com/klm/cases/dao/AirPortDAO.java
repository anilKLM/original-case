package com.klm.cases.dao;

import java.util.List;

import org.json.JSONObject;


public interface AirPortDAO {
	
	public JSONObject getAirPortsData(String urlString, String methodName, String user, String userPs) throws Exception;

}
