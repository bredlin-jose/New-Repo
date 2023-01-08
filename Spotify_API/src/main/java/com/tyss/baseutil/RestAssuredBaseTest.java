package com.tyss.baseutil;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.tyss.commonutils.ExcelUtil;
import com.tyss.commonutils.FileOperation;
import com.tyss.reports.Extent;
import com.tyss.reports.ExtentManager;
import com.tyss.util.RestAssuredUtil;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class RestAssuredBaseTest {
	
	public static Properties prop;
	public static Properties prop_constants;
	public static long get_max_response_time = 2000L;
	public static Logger logger = LoggerFactory.getLogger(RestAssuredBaseTest.class);
	public static final String sDirPath = System.getProperty("user.dir");
	public static final String TESTDATAEXCELPATH = "./src/test/resources/TestData/Data.xlsx";
	public static final String CONFIGPATH = sDirPath + "./config/config.properties";
	public static String className;
	public String testCaseName;
	public static RestAssuredUtil util;
	public static String environment;
	public static RequestSpecification requestSpecification;
	public static RequestSpecBuilder requestSpecBuilder;
	public static ResponseSpecification responseSpecification;
	public static ResponseSpecBuilder responseSpecBuilder;
	public static String accessToken=ExcelUtil.getCellData(TESTDATAEXCELPATH, "Sheet1", 1, 1);
	
	static {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATH);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeSuite(alwaysRun = true)
	public synchronized void createFiles() {
		try {
			logger.info("Folder creation for Extent");
			FileOperation fileOperation = new FileOperation();
			fileOperation.CreateFiles();
		} catch (Exception e) {
			logger.error("Exception while report inititation");
		}
	}
	@BeforeClass
	public synchronized void bsConfig() {
		try {
			
				requestSpecBuilder = new RequestSpecBuilder().setBaseUri(prop.getProperty("spotify_baseUrl"))
					//	.addHeader("Authorization", "Bearer "+ accessToken)
						//.setContentType(ContentType.JSON)
						.log(LogDetail.ALL);
				//requestSpecification=requestSpecBuilder.build();
				
				responseSpecBuilder = new ResponseSpecBuilder().
						expectContentType(ContentType.JSON).
						log(LogDetail.ALL);
				responseSpecification=responseSpecBuilder.build();
						
				
			
		} catch (Exception e) {
			logger.info("Unable to set");
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public synchronized void setExtentReport(Method methodName) {
		
		try {
			this.testCaseName = methodName.getName();
			String description = methodName.getAnnotation(Test.class).description();
			ExtentTest parentExtentTest = Extent.createTest(getClass().getSimpleName());
			ExtentManager.setParentReport(parentExtentTest);
			ExtentTest testReport = ExtentManager.getParentReport().createNode(testCaseName, description);
			ExtentManager.setTestReport(testReport);
		} catch (Exception e) {
			throw new SkipException("Failed to set Extent Report");
		}
	}
	 
}
