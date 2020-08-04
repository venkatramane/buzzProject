package StepDefinition;

import cric.buzz.Pages.HomePage;
import cric.buzz.Pages.LiveScoreBoard;
import cric.buzz.Pages.SchedulePage;
import cric.buzz.Util.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LiveScoreCardDeatils extends TestBase{
	
	HomePage homepage;
	SchedulePage schedulepage;
	LiveScoreBoard livescore;
	
	@Given("^user on home page$")
	public void user_on_home_page() throws Throwable {
		TestBase.initialization();
	   
	}

	@Then("^click livescore and score card butn$")
	public void click_livescore_and_score_card_butn() throws Throwable {
		livescore = new LiveScoreBoard();
		livescore.clickLiveScore();
//		livescore.clickScoreCard();	  
	}

	@Then("^getting score card deatils and writing in workbook$")
	public void getting_score_card_deatils_and_writing_in_workbook() throws Throwable {
		
		livescore.FeedingTitleOfTheMatch();
		livescore.FeedingMatchStatus();
		livescore.Feeding_Scorecard();
	   
	}

}
