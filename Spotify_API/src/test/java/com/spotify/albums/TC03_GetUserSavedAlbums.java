package com.spotify.albums;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC03_GetUserSavedAlbums extends RestAssuredBaseTest{
	@Test
	public void getUserSavedAlbums() {
		
		Response response = RestAssuredUtil.getReq(EndPoints.get_user_saved_album, EndPoints.ok);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
	}

}
