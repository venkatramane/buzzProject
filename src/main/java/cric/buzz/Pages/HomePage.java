package cric.buzz.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cric.buzz.Util.TestBase;

public class HomePage extends TestBase {
	
	//Page Factory / OR
	
 
	
	@FindBy(xpath = "(//a[@class='cb-hm-mnu-itm'])[2]")
	WebElement scheduleBtn;
	
	@FindBy(xpath = "h1[contains(text(),'Cricket Schedule')]")
	WebElement schedulepageTitle;
	




//Initializing the Page Objects

	public HomePage(){
		PageFactory.initElements(driver, this);
		                                                            
	}
		
	//Action
	
	public String validateHomePage(){
		return driver.getTitle();
	}
	

	
	public SchedulePage clickSchedulebtn(){
		
		 scheduleBtn.click();
		
		
		return new SchedulePage();
	}
	
	public String checkSchedulePageTitle(){
		return driver.getTitle();
		
	}
	

	

}