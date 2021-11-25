package com.klm.cases.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.Base64;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CommonUtility {

	public JSONObject prepareConnection(String urlString, String methodName, String userName, String userPs)
			throws Exception {

		HttpURLConnection conn = null;
		JSONObject resultJson = null;
		try {

			URL url = new URL(urlString);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(methodName);
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Basic " + getEnocdedAuthHeader(userName, userPs));

			if (conn.getResponseCode() >= 200 || conn.getResponseCode() <= 299) {
				BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder resultBuilder = new StringBuilder();
				System.out.println("Output from Server .... \n");
				String line;
				while ((line = bReader.readLine()) != null) {
					resultBuilder.append(line);
				}
				resultJson = new JSONObject(resultBuilder.toString());

			} else {
				resultJson = new JSONObject();

			}
			resultJson.put("responseCode", conn.getResponseCode());

		} catch (Exception e) {
			resultJson = new JSONObject();
			resultJson.put("responseCode", 000);

		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return resultJson;
	}

	public String getEnocdedAuthHeader(String userName, String userPs) throws Exception {
		return Base64.getEncoder().encodeToString((userName + ":" + userPs).getBytes());
	}
}
