package RunnerFile;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features="C:\\Users\\VENKATRAMAN\\workspace\\CricbuzzCucumber\\src\\main\\java\\Features\\liveMatchScoreCard.feature", 
		glue={"StepDefinition"},
		format={"pretty","html:test-output","json:json_output/cucumber.json","junit:junit_xml/cucumber.xml"},
		monochrome=true,
		strict=true,
		dryRun=false
		)			

public class TestRunner {

}
