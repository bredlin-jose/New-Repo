package com.spotify.playlists;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC17_AddItemsPlaylist extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC17'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Add Items to Playlist")
	public void addItemsToPlaylist(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.postRequest(EndPoints.add_items_playlist, EndPoints.created, "playlist_id", ids, "uris", prop.getProperty("uris_tracks"));
		
		RestAssuredUtil.verify_status_code(response, EndPoints.created);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
	}
	
}
