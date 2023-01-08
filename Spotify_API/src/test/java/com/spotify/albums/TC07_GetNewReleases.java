package com.spotify.albums;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC07_GetNewReleases extends RestAssuredBaseTest{
	@Test
	public void getNewReleases() {
		Response response= RestAssuredUtil.getReq(EndPoints.get_new_releases_albums, EndPoints.ok);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
	}
}
