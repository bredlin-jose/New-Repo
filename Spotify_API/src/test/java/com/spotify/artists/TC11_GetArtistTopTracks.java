package com.spotify.artists;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC11_GetArtistTopTracks {
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC11'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Artists Top Tracks")

	public void getArtistTopTracks(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.getRequest(EndPoints.get_artist_top_track, EndPoints.ok, "id", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
	}
}
