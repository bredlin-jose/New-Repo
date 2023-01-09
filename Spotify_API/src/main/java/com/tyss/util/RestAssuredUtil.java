package com.tyss.util;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.reports.ExtentManager;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredUtil extends RestAssuredBaseTest {
	public static TakesScreenshot screenshot;
	public static JSONObject jobj = new JSONObject();

	/**
	 * Description Method to provide info in the log,testNg reports
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void info(String message) {
		RestAssuredBaseTest.logger.info(message);
		// ExtentHCLManager.getTestReport().info(message);
	}

	/**
	 * Description Method for the error updation in logs
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void error(String message) {
		RestAssuredBaseTest.logger.error(message);
		// ExtentHCLManager.getTestReport().error(message);
	}

	/**
	 * Description Method to provide info in the extent report
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void extentinfo(String message) {
		ExtentManager.getTestReport().info(message);
	}

	/**
	 * Description Method for the pass updation in extent Report
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void pass(String message) {
		ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}

	/**
	 * Description Method for the info/error updation in extent Report
	 * 
	 * @author Sajal
	 * @param message
	 * @param color
	 */
	public static void validationinfo(String message, String color) {
		if (color.equalsIgnoreCase("blue"))
			ExtentManager.getTestReport().info(MarkupHelper.createLabel(message, ExtentColor.BLUE));
		else if (color.equalsIgnoreCase("green"))
			ExtentManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
		else if (color.equalsIgnoreCase("red"))
			ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	/**
	 * Description Method for the error updation in extent Report
	 * 
	 * @author Sajal
	 * @param message
	 */
	public static void fail(String message) {
		ExtentManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	/**
	 * Description Method for fetching Current Date Time
	 * 
	 * @author Sajal
	 */
	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}

	/**
	 * Description Method to delete the directory
	 * 
	 * @author Sajal
	 * @param pathToDeleteFolder
	 */
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}

	/**
	 * Description Method to delete folder directory
	 * 
	 * @author Sajal
	 * @param folderToDelete
	 */
	public static void deleteFolderDir(File folderToDelete) {
		File[] folderContents = folderToDelete.listFiles();
		if (folderContents != null) {
			for (File folderfile : folderContents) {
				if (!Files.isSymbolicLink(folderfile.toPath())) {
					deleteFolderDir(folderfile);
				}
			}
		}
		folderToDelete.delete();
	}
	
	/**
	 * Description To create the duration of the Test Run
	 * 
	 * @author Sajal
	 * @param millis
	 */
	public static String formatDuration(long millis) {
		long seconds = (millis / 1000) % 60;
		long minutes = (millis / (1000 * 60)) % 60;
		long hours = millis / (1000 * 60 * 60);

		StringBuilder b = new StringBuilder();
		b.append(hours == 0 ? "00" : hours < 10 ? String.valueOf("0" + hours) : String.valueOf(hours));
		b.append(":");
		b.append(minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) : String.valueOf(minutes));
		b.append(":");
		b.append(seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) : String.valueOf(seconds));
		return b.toString();
	}

	

	/**
	 * Description Capture the screenshot of the complete screen
	 * 
	 * @author Sajal
	 * @param path
	 * @return destinationPath
	 */
	public static String getScreenShot(String path) {
		File src = (screenshot.getScreenshotAs(OutputType.FILE));
		String currentDateTime = getCurrentDateTime();
		String destinationPath = path + RestAssuredBaseTest.className + "-" + currentDateTime + ".png";
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "./Screenshots/" + RestAssuredBaseTest.className + "-" + currentDateTime + ".png";
	}


	public static String getJsonData(Response response, String path) {
		String jsonData = response.body().jsonPath().getString(path);
		return jsonData;
	}

	public static void verify_response_code(Response response, int response_code) {
		response.then().statusCode(response_code);
	}

	public static void verify_time(Response response, long get_max_response_time) {
		response.then().assertThat().time(Matchers.lessThan(get_max_response_time), TimeUnit.SECONDS);
	}

	public void verifyResponseKeyValues(Response response, String key, String val) {
		response.then().assertThat().body(key, Matchers.equalTo(val));
	}

	public String getResponseContentType(Response response) {
		return response.getContentType();
	}

	public String getResponseBody(Response response) {
		return response.getBody().asString();
	}

	public static JSONObject data() {
		JSONObject jobj = new JSONObject();
		jobj.put("name", prop.getProperty("name"));
		jobj.put("job", prop.getProperty("job"));
		jobj.put("email", prop.getProperty("email"));
		jobj.put("password", prop.getProperty("password"));
		return jobj;

	}

	public synchronized static Response getMethodWithQueryParams(String endpoint, int status_code, String queryKey,
			String queryValue) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addQueryParam(queryKey, queryValue).build();
			response = given().spec(requestSpecification).when().get(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Status code is: " + status_code);
			info("Status code is: " + status_code);
			response.then().assertThat().time(Matchers.lessThan(get_max_response_time), TimeUnit.SECONDS);
			extentinfo("Response time is less than " + get_max_response_time);
			info("Response time is less than " + get_max_response_time);
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response postMethod(String endpoint, int status_code) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).body(data()).contentType(ContentType.JSON).post(endpoint)
					.then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Status code is: " + status_code);
			info("Status code is: " + status_code);
			response.then().assertThat().time(Matchers.lessThan(get_max_response_time), TimeUnit.SECONDS);
			extentinfo("Response time is less than " + get_max_response_time);
			info("Response time is less than " + get_max_response_time);
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response putMethod(String endpoint, int status_code) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).body(data()).contentType(ContentType.JSON).when()
					.put(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Status code is: " + status_code);
			info("Status code is: " + status_code);
			response.then().assertThat().time(Matchers.lessThan(get_max_response_time), TimeUnit.SECONDS);
			extentinfo("Response time is less than " + get_max_response_time);
			info("Response time is less than " + get_max_response_time);
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response deleteMethod(String endpoint, int status_code) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).when().delete(endpoint).then().log().all().extract()
					.response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Status code is: " + status_code);
			info("Status code is: " + status_code);
			response.then().assertThat().time(Matchers.lessThan(get_max_response_time), TimeUnit.SECONDS);
			extentinfo("Response time is less than " + get_max_response_time);
			info("Response time is less than " + get_max_response_time);
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	// Trello API

	public synchronized static Response postReq(String endpoint, int status_code, String queryKey1, String queryValue1,
			String queryKey2, String queryValue2) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addQueryParam("key", prop.getProperty("key"))
					.addQueryParam("token", prop.getProperty("token")).build();
			response = given().spec(requestSpecification).queryParam(queryKey1, queryValue1)
					.queryParam(queryKey2, queryValue2).contentType(ContentType.JSON).when().post(endpoint).then().log()
					.all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Post Request PASSED");
			info("Post Request PASSED");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response postReq(String endpoint, int status_code, String queryKey1, String queryValue1,
			String queryKey2, String queryValue2, String queryKey3, String queryValue3) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addQueryParam("key", prop.getProperty("key"))
					.addQueryParam("token", prop.getProperty("token")).build();
			response = given().spec(requestSpecification).queryParam(queryKey1, queryValue1)
					.queryParam(queryKey2, queryValue2).queryParam(queryKey3, queryValue3).contentType(ContentType.JSON)
					.when().post(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Put Request PASSED");
			info("Put Request PASSED");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response postReq(String endpoint1, String endpoint2, int status_code, String queryKey1,
			String queryValue1, String queryKey2, String queryValue2, String pathValue) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addQueryParam("key", prop.getProperty("key"))
					.addQueryParam("token", prop.getProperty("token")).build();
			response = given().spec(requestSpecification).queryParam(queryKey1, queryValue1)
					.queryParam(queryKey2, queryValue2).contentType(ContentType.JSON).when()
					.post(endpoint1 + pathValue + endpoint2).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Put Request PASSED");
			info("Put Request PASSED");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	// Spotify

	public static void verify_status_code(Response response, int expected_code) {
		int actual_code = response.getStatusCode();
		info("Status Code is: " + actual_code);
		extentinfo("Status Code is: " + actual_code);
		Assert.assertEquals(actual_code, expected_code);

	}

	public static void verify_response_time(Response response, long expected_time) {
		long actual_time = response.getTime();
		info("Response time is: " + actual_time);
		extentinfo("Response time is: " + actual_time);
		Assert.assertTrue(actual_time < expected_time);
		info("Response time less than: " + expected_time + " MilliSeconds");
		extentinfo("Response time less than: " + expected_time + " MilliSeconds");

	}

	public static void verify_from_response(Response response, String expected, String path) {
		try {
			String actual = response.body().jsonPath().getString(path);
			Assert.assertEquals(actual, expected);
			extentinfo("Actual and Expected are Same");
			info("Actual and Expected are Same");
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
	}

	public synchronized static Response getReq(String endpoint, int status_code, String pathValue) {
		Response response;
		try {

			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).when().get(endpoint + pathValue)
					.then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Get Details Successfully");
			info("Get Details Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response getReq(String endpoint, int status_code, String queryKey, String queryValue) {
		Response response;
		try {

			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).queryParam(queryKey, queryValue)
					.when().get(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Get Details Successfully");
			info("Get Details Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response getReq(String endpoint, int status_code) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.build();
			System.err.println("Bredlin");
			response = given().spec(requestSpecification).auth().oauth2(accessToken).when().get(endpoint).then().log()
					.all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Get Details Successfully");
			info("Get Details Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response putReq(String endpoint, int status_code, String queryKey, String queryValue) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).queryParam(queryKey, queryValue)
					.when().put(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Updated Successfully");
			info("Updated Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to Update");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response deleteReq(String endpoint, int status_code, String queryKey,
			String queryValue) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addQueryParam(queryKey, queryValue).build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).when().delete(endpoint).then()
					.log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Deleted Successfully");
			info("Deleted Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to Delete");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response getMethodforAuthorization(HashMap<String, String> querParamMap,
			String endpoint) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addQueryParams(querParamMap).build();
			response = given().spec(requestSpecification).when().get(endpoint).then().log().all().extract().response();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response postMethodforAuthroization(HashMap<String, String> querParamMap,
			String endpoint) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).formParams(querParamMap).post(endpoint).then().log().all()
					.extract().response();
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response getRequest(String endpoint, int status_code, String pathKey, String pathValue) {
		Response response;
		try {

			requestSpecification = requestSpecBuilder.addPathParam(pathKey, pathValue).build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).when().get(endpoint).then().log()
					.all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Get Details Successfully");
			info("Get Details Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response getRequest(String endpoint, int status_code, String pathValue, String queryKey,
			String queryValue) {
		Response response;
		try {

			requestSpecification = requestSpecBuilder.addPathParam("id", pathValue).addQueryParam(queryKey, queryValue)
					.build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).when().get(endpoint).then().log()
					.all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Get Details Successfully");
			info("Get Details Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response postReq(String endpoint, int status_code, String pathKey, String pathValue,
			HashMap<String, String> map) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addPathParam(pathKey, pathValue).build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).body(map)
					.contentType(ContentType.JSON).when().post(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Created Successfully");
			info("Created Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to Create");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response putReq(String endpoint, int status_code, String pathKey, String pathValue,
			HashMap<String, String> map) {
		Response response;
		try {
			requestSpecification = requestSpecBuilder.addPathParam(pathKey, pathValue).build();
			response = given().spec(requestSpecification).auth().oauth2(accessToken).body(map)
					.contentType(ContentType.JSON).when().put(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Created Successfully");
			info("Created Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to Create");
			Assert.fail();
		}
		return null;
	}

	public synchronized static Response postRequest(String endpoint,int status_code, String pathKey, String pathValue, String queryKey, String queryValue) {
		Response response;
		try {
			
			requestSpecification= requestSpecBuilder.addPathParam(pathKey, pathValue).addQueryParam(queryKey, queryValue).build();
			response=given().spec(requestSpecification).auth().oauth2(accessToken)
					.when().post(endpoint).then().log().all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Created Successfully");
			info("Created Successfully");
			return response;
		} catch (Exception e) {
			error("Unable to create");
			Assert.fail();
		}
		return null;
	}

//	public static void addQueryParm(String queryParamKey) throws FileNotFoundException, IOException, ParseException {
//		try {
//			if(queryParamKey.equals(null)) {
//				info("This request does not have Query Param");
//			}else {
//				String[] queryKey=null;
//				HashMap<String, Object> paramMap = new HashMap<String, Object>();
//				JSONParser jsonParser= new JSONParser();
//				
//				try {
//					JSONObject jsonObject=(JSONObject)jsonParser.parse(new FileReader("./src/test/resources/QueryParams.json"));
//					try {
//						queryKey=queryParamKey.split(",");
//						for (int i = 0; i < queryKey.length; i++) {
//							paramMap.put(queryKey[i], jsonObject.get(queryKey[i]));
//							requestSpecBuilder.addQueryParams(paramMap);	
//						}
//					} catch (Exception e) {
//						requestSpecBuilder.addQueryParam(queryParamKey, jsonObject.get(queryParamKey));
//					}
//				
//				} catch (IOException e) {
//					error(e.getMessage());
//					fail("Unable to add Query Params");
//					Assert.fail("Unable to add Query Params");
//				}	
//			}
//			} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}

//	public static void addQueryParm(String queryParamKey) throws FileNotFoundException, IOException, ParseException {
//
//			try {
//				if(queryParamKey.equals(null)) {
//					info("This request does not have Query Param");
//				}else {
//					JSONParser jsonParser= new JSONParser();
//					JSONObject jsonObject=(JSONObject)jsonParser.parse(new FileReader("./src/test/resources/QueryParams.json"));
//					String[] queryKey=queryParamKey.split(",");
//					for (int i = 0; i < queryKey.length; i++) {
//						requestSpecBuilder.addQueryParam(queryKey[i], jsonObject.get(queryKey[i]));
//					}
//					info("Query Param Added to the request");
//				}
//			} catch (FileNotFoundException e) {
//				error("Unable to add Query Param");
//				Assert.fail("Unable to add Query Param");
//			} 
//			}
	
	public synchronized static Response get(String endpoint, int status_code, String queryParamKey, String queryParamValue, String pathParamKey, String pathParamValue) {
		Response response;
		try {
			addHeader();
			addQueryParm(queryParamKey, queryParamValue);
			addPathParm(pathParamKey,pathParamValue);
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).when().get(endpoint).then().log()
					.all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Get Details Successfully");
			info("Get Details Successfully");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to get the details");
			Assert.fail();
		}
		return null;
	}
	
	public synchronized static Response put(String endpoint, int status_code, String queryParamKey, String queryParamValue, String pathParamKey, String pathParamValue) {
		Response response;
		try {
			addHeader();
			addQueryParm(queryParamKey, queryParamValue);
			addPathParm(pathParamKey,pathParamValue);
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).when().put(endpoint).then().log()
					.all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Updated Successfully");
			info("Updated Successfully");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to update the details");
			Assert.fail();
		}
		return null;
	}
	
	public synchronized static Response delete(String endpoint, int status_code, String queryParamKey, String queryParamValue, String pathParamKey, String pathParamValue) {
		Response response;
		try {
			addHeader();
			addQueryParm(queryParamKey, queryParamValue);
			addPathParm(pathParamKey,pathParamValue);
			requestSpecification = requestSpecBuilder.build();
			response = given().spec(requestSpecification).when().delete(endpoint).then().log()
					.all().extract().response();
			response.then().assertThat().statusCode(status_code);
			extentinfo("Updated Successfully");
			info("Updated Successfully");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to update the details");
			Assert.fail();
		}
		return null;
	}
	
	public static void addQueryParm(String queryParamKey, String queryParamValue) throws FileNotFoundException, IOException, ParseException {

		try {
			JSONParser jsonParser= new JSONParser();
			JSONObject jsonObject=(JSONObject)jsonParser.parse(new FileReader("./src/test/resources/TestData/QueryParams.json"));
			if(queryParamKey.equals("")) {
				info("This request does not have Query Param");
			}else if (queryParamKey.equals("ids")) {
				requestSpecBuilder.addQueryParam(queryParamKey, queryParamValue);
			}else {
				String[] queryKey=queryParamKey.split(",");
					for (int i = 0; i < queryKey.length; i++) {
						requestSpecBuilder.addQueryParam(queryKey[i], jsonObject.get(queryKey[i]));	
					}
				info("Query Param Added to the request");
			}
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to add Query Param");
			Assert.fail("Unable to add Query Param");
			} 
		}
	
	public static void addPathParm(String pathParamKey, String pathParamValue) {
		try {
			if (pathParamKey.equals("")) {
				info("This request does not have Path Param");	
			}else {
				requestSpecBuilder.addPathParam(pathParamKey, pathParamValue);
				info("Path Param Added to the request");
			}
		} catch (Exception e) {
			e.printStackTrace();
			error("Unable to add Path Param");
			Assert.fail("Unable to add Path Param");
		}
	}
	
	public static void addHeader() {
		requestSpecBuilder.addHeader("Authorization", "Bearer "+ accessToken);
	}
	
	public static void jsonSchemaValidation(Response response,String jsonPath) {
		try {
			response.then().assertThat().body(matchesJsonSchemaInClasspath(jsonPath));
			extentinfo("Json Schema Validation pass ");
			info("Json Schema Validation pass ");
		}catch (Exception |AssertionError e) {
			e.printStackTrace();
			error(e.getMessage());
			fail("Json Schema Validation fail");
			Assert.fail("Json Schema Validation fail");
		}
	}
	
	
	
	
	
}