package com.spotify.tracks;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC28_GetTrackAudioFeatures extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC28'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Track audio features")

	public void saveTrackForCurrentUser(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.getReq(EndPoints.get_track_audio_features, EndPoints.ok, "ids", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);		
	}		

}
