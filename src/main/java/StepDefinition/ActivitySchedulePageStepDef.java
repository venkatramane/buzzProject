package StepDefinition;

import java.io.FileInputStream;

import cric.buzz.Pages.HomePage;
import cric.buzz.Pages.SchedulePage;
import cric.buzz.Util.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ActivitySchedulePageStepDef extends TestBase {
	
	HomePage homepage;
	SchedulePage schedulepage;
	
	@Given("^user on schedule page$")
	public void user_on_schedule_page() throws Exception {
		
		TestBase.initialization();
		homepage = new HomePage();
	}

	@Then("^create workbook in step definition$")
	public void create_workbook_in_step_definition() throws Exception  {
		schedulepage = new SchedulePage();
		schedulepage = homepage.clickSchedulebtn();
		

		schedulepage.scheduleHeadTitle();

	}
	
	@Then("^create workbook for austrilan Series$")
	public void create_workbook_for_austrilan_Series() throws Throwable {
		schedulepage.clickTeam();
		schedulepage.onlyAustrilanSeries();
	}


	

}
