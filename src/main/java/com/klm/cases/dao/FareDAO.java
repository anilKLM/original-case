package com.klm.cases.dao;

import org.json.JSONObject;

public interface FareDAO {
	public JSONObject getFareData(String urlString, String methodName, String origin, String destination, String user, String userPs) throws Exception;
}
