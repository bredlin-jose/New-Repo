package com.spotify.artists;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC12_GetArtistRelatedArtist extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC12'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Artists Related Artists")

	public void getArtistTopTracks(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.getRequest(EndPoints.get_artist_top_track, EndPoints.ok, ids, "market", "IN");
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);

	}
}
