package cric.buzz.Pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import cric.buzz.Util.TestBase;

	public class SchedulePage extends TestBase{

//	Page Factory / OR
		
//		@FindBy(xpath="//div[@class='cb-lv-grn-strip text-bold']")
//		List<WebElement> dateOnly;
		//Scenario 1
		@FindBy(xpath="((//div[@class='cb-lv-grn-strip text-bold' and not (contains(text(),'SAT'))])/parent::div[@class='cb-col-100 cb-col'])")
		List<WebElement> schedule;
		//Scenario 2
		@FindBy(xpath = "//a[contains(text(),'Teams')]")
		WebElement teamBtn;
		
		@FindBy(xpath = "(//a[contains(text(),'Australia')])[2]")
		WebElement austrilaFlag;
		
		@FindBy(xpath = "(//a[contains(text(),'Schedule')])[2]")
		WebElement scheduleAustrila;
		
		@FindBy(xpath = "//div[@class='cb-col-100 cb-col cb-series-brdr cb-series-matches ng-scope']")
		List<WebElement> austrilianMatch;

    
	public SchedulePage(){
		PageFactory.initElements(driver, this);
		                                                            
	}
	// Scenario 1
	public void scheduleHeadTitle() throws Exception{
		FileInputStream fis = new FileInputStream("E:\\EXceL Automate\\Cricbuzz_Project.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		org.apache.poi.xssf.usermodel.XSSFSheet sheet = wb.createSheet("Schedule");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int scroll=0;
		int scroll2=1300;
		for(int l=0;l<15;l++){
		js.executeScript("window.scrollBy("+scroll+","+scroll2+")");
		
		scroll=scroll2;
		scroll2=scroll2+1300;
		}
		
		for(int i=0;i<schedule.size();i++){
			sheet.createRow(i);
			
			String ss=schedule.get(i).getText();
			String daa = ss.substring(0, 16);
			sheet.getRow(i).createCell(0).setCellValue(daa);
			System.out.println(daa);
			
			System.out.println(ss);
			String drim=  ss.substring(16);
			sheet.getRow(i).createCell(1).setCellValue(drim);
		}
		
		FileOutputStream op=new FileOutputStream("E:\\EXceL Automate\\Cricbuzz_Project.xlsx");
		wb.write(op);
		wb.close();

	}
	
	public void clickTeam(){
		teamBtn.click();
		austrilaFlag.click();
		scheduleAustrila.click();
		
	}
	// Scenario 2(Australian Series)
	public void onlyAustrilanSeries() throws Exception{
		FileInputStream fis = new FileInputStream("E:\\EXceL Automate\\Cricbuzz_Project.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		org.apache.poi.xssf.usermodel.XSSFSheet sheet = wb.createSheet("Austrila Matches");
		
	  sheet.createRow(0);
	  sheet.getRow(0).createCell(0).setCellValue("Match Date");
	  sheet.getRow(0).createCell(1).setCellValue("Match");
	  sheet.getRow(0).createCell(2).setCellValue("Match Time");
	  
	  
	  
	  
	  for(int aus=0;aus<austrilianMatch.size();aus++){
		  
		  sheet.createRow(aus+1);
		  
		  String date = driver.findElement(By.xpath("(//div[@class='cb-col-25 cb-col pad10 schedule-date  ng-isolate-scope'])["+(aus+1)+"]")).getText();
		  String country = driver.findElement(By.xpath("(//div[@class='cb-col-60 cb-col cb-srs-mtchs-tm cb-ovr-flo'])["+(aus+1)+"]")).getText();
		  String time = driver.findElement(By.xpath("(//div[@class='cb-col-40 cb-col cb-srs-mtchs-tm '])["+(aus+1)+"]")).getText();
		  
		  
		  
		  
		  sheet.getRow(aus+1).createCell(0).setCellValue(date);
		  sheet.getRow(aus+1).createCell(1).setCellValue(country);
		  sheet.getRow(0).createCell(2).setCellValue(time);
		  }
	
		FileOutputStream op=new FileOutputStream("E:\\EXceL Automate\\Cricbuzz_Project.xlsx");
		wb.write(op);
		wb.close();
	  
	
	}
	


	}
