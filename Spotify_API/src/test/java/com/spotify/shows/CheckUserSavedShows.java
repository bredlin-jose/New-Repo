package com.spotify.shows;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.dataproviders.DataProviderFactory;
import com.tyss.dataproviders.DataProviderFileRowFilter;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class CheckUserSavedShows extends RestAssuredBaseTest{
	@DataProviderFileRowFilter(file = "/src/test/resources/TestData/Data.xlsx",sql = "Select * from Sheet2 where testCase ='TC12'")
	@Test(dataProvider = "data1", dataProviderClass = DataProviderFactory.class, description = "Check User Saved Shows")
	public void searchForItem(String testCase, String endpoints, String queryParamKey, String queryParamValue, String pathParamKey, String pathParamValue) {
		
		Response response = RestAssuredUtil.get(endpoints, EndPoints.ok, queryParamKey, queryParamValue, pathParamKey, pathParamValue);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);	
		
		
		
	}

}
