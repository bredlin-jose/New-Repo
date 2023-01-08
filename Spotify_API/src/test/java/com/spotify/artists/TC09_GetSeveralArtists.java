package com.spotify.artists;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC09_GetSeveralArtists extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC9'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Several artists")
	public void getSeveralArtist(String testCase, String ids, String description) {
		Response response = RestAssuredUtil.getReq(EndPoints.get_several_artists, EndPoints.ok, "ids", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
	}
}
