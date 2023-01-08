package com.spotify.albums;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC04_SaveAlbumsForCurrentUser extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC4'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Album")
	public void saveAlbumsForCurrentUser(String testCase, String ids, String description) {
		
		Response response = RestAssuredUtil.putReq(EndPoints.save_album_for_current_user, EndPoints.ok, "ids", ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
	}

}
