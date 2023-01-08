package com.spotify.playlists;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC15_UpdatePlaylistDetails extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC15'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Change Playlist Details")
	public void updatePlaylistDetails(String testCase, String ids, String description) {
		HashMap<String, String> map= new HashMap<String, String>();
		map.put("name", "New Playlist");
		map.put("description", "Updated Playlist Description");
		
		Response response =RestAssuredUtil.putReq(EndPoints.change_playlist_details, EndPoints.ok, "playlist_id", ids, map);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
	}

}
