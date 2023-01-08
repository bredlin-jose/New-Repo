package com.spotify.playlists;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC20_GetUsersPlaylist extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC20'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Users Playlist")

	public void getPlaylists(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.getRequest(EndPoints.get_users_playlist, EndPoints.ok, "user_id",ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);

	}
}
