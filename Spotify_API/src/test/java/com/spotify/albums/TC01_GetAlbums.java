package com.spotify.albums;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC01_GetAlbums extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Spotify where testCase ='TC1'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Get Album")
	public void getAlbums(String testCase, String ids, String description) {
		
		Response response= RestAssuredUtil.getReq(EndPoints.get_album, EndPoints.ok, ids);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
		
	}

}
