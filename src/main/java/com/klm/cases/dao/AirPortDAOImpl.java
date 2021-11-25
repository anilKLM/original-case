package com.klm.cases.dao;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.klm.cases.utilities.CommonUtility;

@Repository
public class AirPortDAOImpl implements AirPortDAO {

	@Autowired
	private CommonUtility commonUtility;
	
	@Override
	public JSONObject getAirPortsData(String urlString, String methodName, String user, String userPs) throws Exception {
		// TODO Auto-generated method stub
		
		return commonUtility.prepareConnection(urlString, "GET", user, userPs);
	}

}
