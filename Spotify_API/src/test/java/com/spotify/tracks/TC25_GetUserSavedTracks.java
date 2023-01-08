package com.spotify.tracks;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC25_GetUserSavedTracks extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC25'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Tracks")

	public void getUserSavedTracks(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.getReq(EndPoints.get_user_saved_tracks, EndPoints.ok);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
	}

}
