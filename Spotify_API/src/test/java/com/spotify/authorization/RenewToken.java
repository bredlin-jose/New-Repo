package com.spotify.authorization;

import java.util.HashMap;

import org.testng.annotations.Test;


import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.commonutils.ExcelUtil;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class RenewToken extends RestAssuredBaseTest{

	@Test
	
	public void get_Token() throws Exception {
		
		HashMap<String, String> query_Param_Map = new HashMap<String,String>();
		
		query_Param_Map.put("client_id", prop.getProperty("client_id"));
		query_Param_Map.put("client_secret", prop.getProperty("client_secret"));
		query_Param_Map.put("grant_type", prop.getProperty("granttype"));
		query_Param_Map.put("refresh_token", prop.getProperty("refresh_token"));
		
		Response response=RestAssuredUtil.postMethodforAuthroization(query_Param_Map, prop.getProperty("renewtoken_url"));
		String accesstoken = RestAssuredUtil.getJsonData(response, "access_token");
		System.out.println(" Accesstoken : "+accesstoken);
		ExcelUtil.setDataExcel("Sheet1", 1, 1, accesstoken);
		
	}
	
}
