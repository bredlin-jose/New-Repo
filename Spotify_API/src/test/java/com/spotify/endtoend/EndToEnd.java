package com.spotify.endtoend;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class EndToEnd extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Sheet2 where testCase ='TC22'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "End To End")
	public void endToend(String testCase, String endpoints, String queryParamKey, String queryParamValue, String pathParamKey, String pathParamValue) {
		
		//Follow Playlist
		Response response_follow_playlist = RestAssuredUtil.put(endpoints, EndPoints.ok, queryParamKey, queryParamValue, pathParamKey, pathParamValue);	
		RestAssuredUtil.verify_status_code(response_follow_playlist, EndPoints.ok);
		RestAssuredUtil.verify_response_time(response_follow_playlist, EndPoints.max_time);
		
		//Unfollow Playlist
		Response response_unfollow_playlist = RestAssuredUtil.delete(endpoints, EndPoints.ok, queryParamKey, queryParamValue, pathParamKey, pathParamValue);
		RestAssuredUtil.verify_status_code(response_unfollow_playlist, EndPoints.ok);
		RestAssuredUtil.verify_response_time(response_unfollow_playlist, EndPoints.max_time);	
		
		
		
	}

}
