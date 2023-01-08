package com.spotify.playlists;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC14_CreatePlaylist extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC14'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Artists Top Tracks")

	public void createPlaylist(String testCase, String ids, String description) {
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("name", "New Playlist");
		map.put("description", "Playlist Description");
		map.put("public", "false");
		
		Response response =RestAssuredUtil.postReq(EndPoints.create_playlist, EndPoints.created, "user_id", ids, map);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.created);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
	}

}
