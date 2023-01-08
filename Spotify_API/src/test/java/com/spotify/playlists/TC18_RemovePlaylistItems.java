package com.spotify.playlists;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC18_RemovePlaylistItems extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC18'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Remove Playlist items")
	public void updatePlaylistDetails(String testCase, String ids, String description) {
		
	}

}
