package ExtentReport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import cric.buzz.Util.TestBase;


public class ConfigFileReader extends TestBase{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
//	@BeforeTest
//	public void setExtent() {
//		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testReport.html");
//		htmlReporter.config().setDocumentTitle("Automation Report"); 
//		htmlReporter.config().setReportName("report-porject 1"); 
//		htmlReporter.config().setTheme(Theme.DARK);
//		
//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
//		
//		extent.setSystemInfo("Host name", "localhost");
//		extent.setSystemInfo("Environemnt", "QA");
//		extent.setSystemInfo("tester", "name");
//		
//		
//	}
//			@AfterTest
//			public void endReport() {
//				extent.flush();
//			} 
//			
			public String getReportConfigPath(){
				String reportConfigPath = prop.getProperty("getReportConfigPath");
				if(reportConfigPath!= null) return reportConfigPath;
				else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"); 
				}



}
