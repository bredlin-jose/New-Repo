package com.spotify.authorization;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class GetToken extends RestAssuredBaseTest{
	
	@Test
	
	public void get_token() {
		
		HashMap<String, String> query_Param_Map = new HashMap<String,String>();
		
		query_Param_Map.put("client_id", prop.getProperty("client_id"));
		query_Param_Map.put("client_secret", prop.getProperty("client_secret"));
		query_Param_Map.put("grant_type", prop.getProperty("grant_type"));
		query_Param_Map.put("redirect_uri", prop.getProperty("redirect_uri"));
		query_Param_Map.put("code", prop.getProperty("code"));
		
		Response response=RestAssuredUtil.postMethodforAuthroization(query_Param_Map, prop.getProperty("renewtoken_url"));
		String accesstoken = RestAssuredUtil.getJsonData(response, "refresh_token");
		System.out.println(accesstoken);
		
	}

}
