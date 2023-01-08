package com.spotify.playlists;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC16_GetPlaylistItems {
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC16'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Playlist Items")

	public void getPlaylistItems(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.getRequest(EndPoints.get_playlist_items, EndPoints.ok, "playlist_id", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
	}
}
