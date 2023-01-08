package com.spotify.playlists;

import org.testng.annotations.Test;

import com.tyss.baseutil.EndPoints;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.util.RestAssuredUtil;

import io.restassured.response.Response;

public class TC21_GetFeaturedPlaylist extends RestAssuredBaseTest{
	@Test
	public void getFeaturedPlaylist() {
		
		Response response = RestAssuredUtil.getReq(EndPoints.get_featured_playlist, EndPoints.ok);
		
		RestAssuredUtil.verify_status_code(response, EndPoints.ok);
		
		RestAssuredUtil.verify_response_time(response, EndPoints.max_time);
		
	}
}
