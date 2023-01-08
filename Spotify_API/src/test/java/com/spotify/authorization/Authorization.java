package com.spotify.authorization;

import java.util.HashMap;

import org.testng.annotations.Test;


import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.util.RestAssuredUtil;

public class Authorization extends RestAssuredBaseTest{
	
	@Test
	
	public void getautorize() {
		
	HashMap<String, String> query_Param_Map = new HashMap<String,String>();
		
		query_Param_Map.put("client_id", prop.getProperty("client_id"));
		query_Param_Map.put("response_type", prop.getProperty("response_type"));
		query_Param_Map.put("scope", prop.getProperty("scope"));
		query_Param_Map.put("redirect_uri", prop.getProperty("redirect_uri"));
		RestAssuredUtil.getMethodforAuthorization(query_Param_Map, prop.getProperty("authorize_Url"));
	}

}
