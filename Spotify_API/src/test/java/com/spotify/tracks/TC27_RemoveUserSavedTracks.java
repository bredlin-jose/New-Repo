package com.spotify.tracks;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC27_RemoveUserSavedTracks extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC27'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Remove user saved tracks")

	public void removeUserSavedTrack(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.deleteReq(EndPoints.remove_user_saved_tracks, EndPoints.ok, "ids", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);		
	}		
}
