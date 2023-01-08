package com.tyss.commonutils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tyss.baseutil.RestAssuredBaseTest;
import com.tyss.reports.Extent;
import com.tyss.util.RestAssuredUtil;

/**
 * Description : Implements setting of the path,creation of the folder structure
 * for Extent reports,screenshots,deletion of the previously created folder.
 * 
 * @author: Sajal, Vikas
 */
public class FileOperation {
	FileVariables fileVariables = new FileVariables();
	public static String reportTimeStamp;

	public void CreateFiles() {
		fileVariables.setDate(new Date());
		fileVariables.setSdf(new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss"));
		fileVariables.setsDate(fileVariables.getSdf().format(fileVariables.getDate()));
		fileVariables.setsStartTime(fileVariables.getsDate());
		reportTimeStamp = RestAssuredUtil.getCurrentDateTime();
		fileVariables.setExtentReportFolderPath(System.getProperty("user.dir") + "/reports");
		fileVariables.setExtentDir(fileVariables.getExtentReportFolderPath() + "/Automation_Report"
				);
		fileVariables.setScreenShotPath(fileVariables.getExtentDir() + "/Screenshots/");

		RestAssuredBaseTest.logger.info("ExtentDir:-" + fileVariables.getExtentDir());

		/* delete extent folder if it exists before running suite */
		RestAssuredUtil.deleteDir(fileVariables.getExtentReportFolderPath());

		try {
			File file = new File(fileVariables.getExtentDir());
			if (!(file.exists())) {
				boolean extentFolderStatus = file.mkdirs();
				if (extentFolderStatus == true) {
					new Extent().initReport(fileVariables.getExtentDir());
//					RestAssuredUtil.copyFile(fileVariables.getExtentDir(),BaseTest.prop.getProperty("testYantraLogo"));
//					RestAssuredUtil.copyFile(fileVariables.getExtentDir(),BaseTest.prop.getProperty("clientLogo"));
				} else
					RestAssuredBaseTest.logger.error("Extent folder not created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Setting ScreenShot Report Location
		try {
			File screenShot = new File(FileVariables.getScreenShotPath());
			if (!(screenShot.exists())) {
				boolean screenshotFolderStatus = screenShot.mkdirs();
				if (screenshotFolderStatus == true)
					RestAssuredBaseTest.logger.info("Screenshot folder created");
				else
					RestAssuredBaseTest.logger.error("Screenshot folder not created");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
