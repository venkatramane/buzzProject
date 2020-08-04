package StepDefinition;

import cric.buzz.Pages.HomePage;
import cric.buzz.Pages.SchedulePage;
import cric.buzz.Util.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;

public class StepDefSchedulePage extends TestBase{
	
	HomePage homepage;
	SchedulePage schedulepage = new SchedulePage();
	
	@Given("^open the browser using cricbuzz link$")
	public void open_the_browser_using_cricbuzz_link() throws Throwable {
		TestBase.initialization();
	   
	}
	
	@Then("^validate home page title$")
	public void validate_home_page_title() throws Throwable {
		
		homepage = new HomePage();
		String title = homepage.validateHomePage();
		Assert.assertEquals("Cricket Score, Schedule, Latest News, Stats & Videos | Cricbuzz.com", title);
		
	}


	@Then("^click on schedule button$")
	public void click_on_schedule_button() throws Throwable {
		
		schedulepage = homepage.clickSchedulebtn();
	   
	}

	@Then("^moving to schedule page and validate schedule page title$")
	public void moving_to_schedule_page_and_validate_schedule_page_title() throws Throwable {
	   String ScheduleTitle = homepage.checkSchedulePageTitle();
	   Assert.assertEquals("Cricket Schedule - International, domestic and T20 matches - Cricbuzz | Cricbuzz.com", ScheduleTitle);
	}

}
