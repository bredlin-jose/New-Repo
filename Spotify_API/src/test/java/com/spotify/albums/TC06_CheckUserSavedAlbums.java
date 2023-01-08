package com.spotify.albums;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC06_CheckUserSavedAlbums extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC6'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Check User Saved Album")
	public void checkUserSavedAlbums(String testCase, String ids, String description) {
			
		Response response = RestAssuredUtil.getReq(EndPoints.check_user_saved_albums, EndPoints.ok, "ids", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
	}
}
