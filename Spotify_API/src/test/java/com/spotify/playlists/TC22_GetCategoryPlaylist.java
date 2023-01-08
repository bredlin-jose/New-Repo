package com.spotify.playlists;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC22_GetCategoryPlaylist extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC22'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Category Playlist")

	public void getCategoryPlaylist(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.getRequest(EndPoints.get_category_playlist, EndPoints.ok, "category_id", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
	}

}
