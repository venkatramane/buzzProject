package RunnerFile;

import java.io.File;

import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import ExtentReport.FileReaderManager;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(

		features="C:\\Users\\VENKATRAMAN\\workspace\\CricbuzzCucumber\\src\\main\\java\\Features\\liveMatchScoreCard.feature", 
		glue={"StepDefinition"},
				plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},

		format={"pretty","html:test-output","json:json_output/cucumber.json","junit:junit_xml/cucumber.xml"},
		monochrome=true,
		strict=true,
		dryRun=false
		)			

public class TestRunner {
	
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
		}


}
