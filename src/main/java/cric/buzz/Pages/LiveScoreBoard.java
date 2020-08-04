package cric.buzz.Pages;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cric.buzz.Util.TestBase;

public class LiveScoreBoard extends TestBase{
	
	
	
	static File source;
	static FileInputStream fis;
	static FileOutputStream fos;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet=null;

	public static String status=null;
	public static String matchTitle=null;
	public static XSSFColor statusColor=null;
	static String title="";
	static int lastRow=0;
	static int count=0;
	static 	int noOfBatsmen=0;
	
	static XSSFColor color=new XSSFColor(Color.BLUE);
	static XSSFColor headerWhite=new XSSFColor(Color.WHITE);
	static XSSFColor headerColor=new XSSFColor(Color.LIGHT_GRAY);
	static XSSFColor headerTextcolor=new XSSFColor(Color.gray);
	
//	@FindBy(xpath="//a[contains(text(),'Live Scores')]")        
//	WebElement liveScoreBtn;
//
//	@FindBy(xpath="//div[@class='cb-col cb-scrcrd-status cb-col-100 cb-text-live ng-scope']")     
//	public static WebElement matchStatusLive;
//	
	
	public LiveScoreBoard() throws IOException {                                                       // Constructor
		
		
//			PageFactory.initElements(driver, this);
			                                                            
		
		
		
        source=new File("E:\\EXceL Automate\\Cricbuzz_Project.xlsx");
		
		fis =new FileInputStream(source);
		
		workbook=new XSSFWorkbook(fis);
		
		 int index=0;
		
			
			sheet=workbook.getSheet("Scenario_3");                                
			
			if(sheet!=null) {
				index =workbook.getSheetIndex("Scenario_3");                     // To get the index of the sheet
				
				workbook.removeSheetAt(index);                                   // Removing the sheet using index
			}
			sheet=workbook.createSheet("Scenario_3");
	}

	public void clickLiveScore(){
//		liveScoreBtn.click();
		driver.findElement(By.xpath("//a[contains(text(),'Live Scores')]")).click();
		driver.findElement(By.xpath("(//a[contains(text(),'Scorecard')])[1]")).click();
	}
	
	public void FeedingTitleOfTheMatch() {
		
		title=driver.findElement(By.xpath("//h1[contains(text(),'Live Cricket Score')]")).getText();
		 XSSFRow row=sheet.createRow(0);                                                    // Setting font Style for Headers
			XSSFCell cell=row.createCell(0);
			XSSFCellStyle style=workbook.createCellStyle();
			XSSFFont font=workbook.createFont();
			font.setBold(true);                                                             // Set Bold for font
			style.setFont(font); 
//			font.setColor(color);
			cell.setCellStyle(style);
			cell.setCellValue(title); 
		    
		
	}
	
	
	public void FeedingMatchStatus() {                                                         // Match Status
		
		 String colour=null;
			
		
		try {
			WebElement status1= driver.findElement(By.xpath("//div[@class='cb-col cb-scrcrd-status cb-col-100 cb-text-live ng-scope']"));
			colour="Red";
			status=status1.getText();
			
		
			}
			catch (Exception e) {
				WebElement status1= driver.findElement(By.xpath("//div[@class='cb-col cb-scrcrd-status cb-col-100 cb-text-complete ng-scope']"));
				colour="Blue";
				status=status1.getText();
			}
		
		    XSSFRow row=sheet.createRow(1);                                                    
			XSSFCell cell=row.createCell(0);
			XSSFCellStyle style=workbook.createCellStyle();
			XSSFFont font=workbook.createFont();
			if(colour.equals("Red")) {
				statusColor=new XSSFColor(Color.RED);
			}
			else {
				statusColor=new XSSFColor(Color.BLUE);
			}
			font.setBold(false);      
			style.setFont(font);
			font.setColor(statusColor);
			cell.setCellStyle(style);
			cell.setCellValue(status); 
		
	}
	
	
	public void Feeding_Scorecard() {                                                                               // Batting Scorecard
		
		
		
		List<WebElement> InningsCount= driver.findElements(By.xpath("(//div[@class='ng-scope'])"));                // Number of Innings
		System.out.println("Number of Innings : "+ (InningsCount.size()-1));
		
	try {	
		
		for(count=1;count<InningsCount.size();count++) {
		
			
			lastRow=sheet.getLastRowNum()+1; 
			sheet.createRow(lastRow);
			
			List<WebElement> scorecardHeader=driver.findElements(By.xpath("//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-100 cb-scrd-hdr-rw']//child::span"));
	        	
			for(int i=0;i<=6;i++) {
				
				if(i==0) {
					    XSSFRow row=sheet.getRow(lastRow);
				     	XSSFCell cell=row.createCell(i);
				     	XSSFCellStyle style=workbook.createCellStyle();
				     	
				     	style.setFillForegroundColor(headerTextcolor);                                           // Fill cell with Gray foreground 
				         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				     	XSSFFont font=workbook.createFont();
				     	font.setBold(true);
				     	font.setColor(headerWhite);
				     	cell.setCellStyle(style);
				     	cell.setCellValue(scorecardHeader.get(i).getText());
				}
				else if(i>0 && i<6) {
					    XSSFRow row=sheet.getRow(lastRow);
				     	XSSFCell cell=row.createCell(i);
				     	XSSFCellStyle style=workbook.createCellStyle();
				     	style.setFillForegroundColor(headerTextcolor);                                           
				        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				     	XSSFFont font=workbook.createFont();
				     	cell.setCellStyle(style);
				}
				
				else {
					
					    XSSFRow row=sheet.getRow(lastRow);
				     	XSSFCell cell=row.createCell(i);
				     	XSSFCellStyle style=workbook.createCellStyle();
				     	
				     	style.setFillForegroundColor(headerTextcolor);                                         
				         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				     	XSSFFont font=workbook.createFont();
				     	font.setBold(true);
				     	font.setColor(headerWhite);
				     	cell.setCellStyle(style);
				     	cell.setCellValue(scorecardHeader.get(1).getText());
					
				}
			
			
		        
			}
			
			
			
			
			
		
               List<WebElement> batsmenList= driver.findElements(By.xpath("//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-27 ']"));
		
	         	noOfBatsmen=batsmenList.size();

	         	lastRow=sheet.getLastRowNum()+1; 
		
		for(int i=1;i<=noOfBatsmen;i++) {
			
			
          List<WebElement> scoreCardDetails = driver.findElements(By.xpath("(//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-100 cb-scrd-itms'])["+i+"]/child::div"));
       
         

          sheet.createRow(lastRow); 

          int cellCount_1=0;
                                                                                                         //div[@id='innings_"+count+"']
          for(int j=0;j<scoreCardDetails.size();j++) {
        
        	 
        	  
         WebElement scoredcardHeader=driver.findElement(By.xpath("(//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray'])[1]//child::div["+(j+1)+"]"));
         String scoredcardHeaderText=scoredcardHeader.getText();
         
        XSSFRow row=sheet.getRow(lastRow);
     	XSSFCell cell=row.createCell(cellCount_1);
     	XSSFCellStyle style=workbook.createCellStyle();
     	
     	style.setFillForegroundColor(headerColor);                                           // Fill cell with Gray foreground 
         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
     	XSSFFont font=workbook.createFont();
     	font.setBold(true);
     	font.setColor(headerTextcolor);
     	cell.setCellStyle(style);
     	cell.setCellValue(scoredcardHeaderText);
     	
         cellCount_1++;
		}
       
          }
		
		
		
//	 ******************************  Scorecard Header **********************************

	


     	lastRow=sheet.getLastRowNum()+1; 

     for(int i=1;i<=noOfBatsmen;i++) {
	
	
     List<WebElement> scoreCardDetails = driver.findElements(By.xpath("(//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-100 cb-scrd-itms'])["+i+"]/child::div"));
 

  sheet.createRow(lastRow); 

  int cellCount_1=0;
                                                                                                 //div[@id='innings_"+count+"']
  for(int j=0;j<scoreCardDetails.size();j++) {

	  String Innings_1_details= scoreCardDetails.get(j).getText();
	  
	  if(cellCount_1==0) {
		  
		  XSSFRow row=sheet.getRow(lastRow);
	     	XSSFCell cell=row.createCell(cellCount_1);
	     	XSSFCellStyle style=workbook.createCellStyle();
	     	
	     	XSSFFont font=workbook.createFont();
	     	font.setBold(false);
	     	font.setColor(color);
	     	style.setFont(font); 
	     	cell.setCellStyle(style);
	     	cell.setCellValue(Innings_1_details);
	  
        cellCount_1++;
        
	  }
	  
	  else if(cellCount_1==2) {
		  
		  XSSFRow row=sheet.getRow(lastRow);
	     	XSSFCell cell=row.createCell(cellCount_1);
	     	XSSFCellStyle style=workbook.createCellStyle();
	     	
	     	XSSFFont font=workbook.createFont();
	     	font.setBold(true);
	     	style.setFont(font); 
	     	cell.setCellStyle(style);
	     	cell.setCellValue(Innings_1_details);
	  
        cellCount_1++;
        
	  }
        
        else {
        	
        	
        	 XSSFRow row=sheet.getRow(lastRow);
 	     	XSSFCell cell=row.createCell(cellCount_1);
 	     	XSSFCellStyle style=workbook.createCellStyle();
 	     	
 	     	XSSFFont font=workbook.createFont();
 	     	font.setBold(false);
 	     	font.setColor(headerTextcolor);
 	     	style.setFont(font); 
 	     	cell.setCellStyle(style);
 	     	cell.setCellValue(Innings_1_details);
        	  cellCount_1++;
        }
	  
	  
	  }
  
           lastRow++;
}

//   ****************** Scorecard ************************8


		
		
     lastRow=sheet.getLastRowNum()+1;
		
     sheet.createRow(lastRow);
     

     
     String extrasTitle=driver.findElement(By.xpath("//div[@id='innings_"+count+"']//div[contains(text(),'Extras')]")).getText();
     String extrasTotal =driver.findElement(By.xpath("//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-8 text-bold cb-text-black text-right']")).getText();
     String extras=driver.findElement(By.xpath("(//div[@id='innings_"+count+"']//div[@class='cb-col-32 cb-col'])[1]")).getText();
     
      int cellExtras=0;
      
      for(int i=0;i<3;i++) {
     	 
			
			if(i==0) {
				
				 XSSFRow row=sheet.getRow(lastRow);
		 	     	XSSFCell cell=row.createCell(cellExtras);
		 	     	XSSFCellStyle style=workbook.createCellStyle();
		 	     	
		 	     	XSSFFont font=workbook.createFont();
		 	     	font.setBold(true);
		 	     	style.setFont(font); 
		 	     	cell.setCellStyle(style);
		 	     	cell.setCellValue(extrasTitle);
				
			      cellExtras=cellExtras+3;
		      	}	
			
			else if(i==1) {
				
				XSSFRow row=sheet.getRow(lastRow);
	 	     	XSSFCell cell=row.createCell(cellExtras);
	 	     	XSSFCellStyle style=workbook.createCellStyle();
	 	     	
	 	     	XSSFFont font=workbook.createFont();
	 	     	font.setBold(true);
	 	     	style.setFont(font); 
	 	     	cell.setCellStyle(style);
	 	     	cell.setCellValue(extrasTotal);
			
				cellExtras++;
			}
			else{
				
				XSSFRow row=sheet.getRow(lastRow);
	 	     	XSSFCell cell=row.createCell(cellExtras);
	 	     	XSSFCellStyle style=workbook.createCellStyle();
	 	     	
	 	     	XSSFFont font=workbook.createFont();
	 	     	font.setBold(false);
	 	     	font.setColor(headerTextcolor);
	 	     	style.setFont(font); 
	 	     	cell.setCellStyle(style);
	 	     	cell.setCellValue(extras);
		
			}
		}
		
		
//		*************************  Extras **********************
		
		
		

      int cellTotal=0;
		
		lastRow=sheet.getLastRowNum()+1;
       sheet.createRow(lastRow);
       
       String totalTitle=driver.findElement(By.xpath("//div[@id='innings_"+count+"']//div[contains(text(),'Total')]")).getText();
       String totalOverall =driver.findElement(By.xpath("//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-8 text-bold text-black text-right']")).getText();
       String total=driver.findElement(By.xpath("(//div[@id='innings_"+count+"']//div[@class='cb-col-32 cb-col'])[2]")).getText();
       
        for(int i=0;i<3;i++) {
			
      	
			
			if(i==0) {
				
				XSSFRow row=sheet.getRow(lastRow);
	 	     	XSSFCell cell=row.createCell(cellTotal);
	 	     	XSSFCellStyle style=workbook.createCellStyle();
	 	     	
	 	     	XSSFFont font=workbook.createFont();
	 	     	font.setBold(true);
	 	     	style.setFont(font); 
	 	     	cell.setCellStyle(style);
	 	     	cell.setCellValue(totalTitle);
			
			cellTotal=cellTotal+3;
			}	
			
			else if(i==1) {
				XSSFRow row=sheet.getRow(lastRow);
	 	     	XSSFCell cell=row.createCell(cellTotal);
	 	     	XSSFCellStyle style=workbook.createCellStyle();
	 	     	
	 	     	XSSFFont font=workbook.createFont();
	 	     	font.setBold(true);
	 	     	style.setFont(font); 
	 	     	cell.setCellStyle(style);
	 	     	cell.setCellValue(totalOverall);
	 	     	
				cellTotal++;
			}
			else {
				
				XSSFRow row=sheet.getRow(lastRow);
	 	     	XSSFCell cell=row.createCell(cellTotal);
	 	     	XSSFCellStyle style=workbook.createCellStyle();
	 	     	
	 	     	XSSFFont font=workbook.createFont();
	 	     	font.setBold(false);
	 	     	font.setColor(headerTextcolor);
	 	     	style.setFont(font); 
	 	     	cell.setCellStyle(style);
	 	     	cell.setCellValue(total);
				
		
			}
		}
		
		
//		**************************************** Total *********************************
		
        try {
        String yetTobatTitle=driver.findElement(By.xpath("//div[@id='innings_"+count+"']//div[contains(text(),' Yet to Bat ')]")).getText();
        lastRow=sheet.getLastRowNum()+1;
        
        sheet.createRow(lastRow).createCell(0).setCellValue(yetTobatTitle);
        
        String yetToBat=driver.findElement(By.xpath("//div[@id='innings_1']//div[@class='cb-col-73 cb-col']")).getText();
        
        XSSFRow yetToBatRow=sheet.getRow(lastRow);
     	XSSFCell yetToBatCell=yetToBatRow.createCell(1);
     	XSSFCellStyle yetToBatStyle=workbook.createCellStyle();
     	
     	XSSFFont yetToBatFont=workbook.createFont();
     	yetToBatFont.setBold(false);
     	yetToBatFont.setColor(color);
     	yetToBatStyle.setFont(yetToBatFont); 
     	yetToBatCell.setCellStyle(yetToBatStyle);
     	yetToBatCell.setCellValue(yetToBat);
        }
        catch(NoSuchElementException e) {
        	e.getMessage();
        }
       
        
        
        //  ************************* Yet to Bat *********************************
        
     	
     	
        lastRow=sheet.getLastRowNum()+1;
        
        WebElement fowHeader=driver.findElement(By.xpath("//div[@id='innings_"+count+"']//div[contains(text(),'Fall of Wickets')]"));
	       	
        String fowTitle = fowHeader.getText();
        
	     	XSSFRow rowFOWHeader=sheet.createRow(lastRow);
	     	XSSFCell cellFOWHeader=rowFOWHeader.createCell(0);
	     	XSSFCellStyle styleFOWHeader=workbook.createCellStyle();
	     	
	     	XSSFFont fontFOWHeader=workbook.createFont();
	     	fontFOWHeader.setBold(true);
	     	styleFOWHeader.setFont(fontFOWHeader); 
	     	cellFOWHeader.setCellStyle(styleFOWHeader);
	     	cellFOWHeader.setCellValue(fowTitle);

		
	                         // FOW Header
		
		
		List<WebElement> fallOfWickets=driver.findElements(By.xpath("//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-100 cb-col-rt cb-font-13']//child::span"));
		lastRow=sheet.getLastRowNum()+1;
		
		sheet.createRow(lastRow);
		int cellFOW=0;
		for(int i=0;i<fallOfWickets.size();i++) {                        
			
			String fowText=fallOfWickets.get(i).getText();
			
			int indexOfBracket = fowText.indexOf('(');
			int indexOfComma = fowText.indexOf(',');
			String playerName=fowText.substring(indexOfBracket, indexOfComma);
			
//			sheet.addMergedRegion(CellRangeAddress(lastRow,cellFOW));
			
			
			XSSFRow row=sheet.getRow(lastRow);
 	     	XSSFCell cell=row.createCell(cellFOW);
 	     	XSSFCellStyle style=workbook.createCellStyle();
 	     	
 	     	XSSFFont font=workbook.createFont();
 	     	font.setBold(false);
 	     	font.setColor(color);
 	      
 	     	XSSFRichTextString richString = new XSSFRichTextString(fowText);
 	     	richString.applyFont((indexOfBracket+1), indexOfComma, font);
 	     	cell.setCellValue(richString);
			
				                                                                                             // Fall Of Wickets
				cellFOW++;
			}
		
		
//		     **************************** FOW **********************************
	
		
		lastRow=sheet.getLastRowNum()+1;
		
		sheet.createRow(lastRow);
		
		int cellBowCard=0;
		
		List<WebElement> bowlerCardHeader = driver.findElements(By.xpath("(//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-100 cb-scrd-sub-hdr cb-bg-gray'])[2]//child::div"));
		
		for(int i=0;i<bowlerCardHeader.size();i++) {
			
		
			String bowlerHeader=bowlerCardHeader.get(i).getText();
			
			 XSSFRow row=sheet.getRow(lastRow);
		     	XSSFCell cell=row.createCell(cellBowCard);
		     	XSSFCellStyle style=workbook.createCellStyle();
		     	style.setFillForegroundColor(headerColor);                                           // Fill cell with Gray foreground 
		         style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		     	XSSFFont font=workbook.createFont();
		     	font.setBold(true);
		     	font.setColor(headerTextcolor);
		     	cell.setCellStyle(style);
		     	cell.setCellValue(bowlerHeader);
			
			cellBowCard++;
		
		
			
		}
		
		
        lastRow=sheet.getLastRowNum()+1;
		
		List<WebElement> bowlersList=driver.findElements(By.xpath("//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-40']"));
		int noOfBowler=bowlersList.size();
		
		for(int i=1;i<=noOfBowler;i++) {
			
			sheet.createRow(lastRow);
			int cellBowler=0;
			List<WebElement> bowlerCard=driver.findElements(By.xpath("(//div[@id='innings_"+count+"']//div[@class='cb-col cb-col-100 cb-scrd-itms '])["+i+"]//child::div"));
			
			
			
			for(int j=0;j<bowlerCard.size();j++) {
				
				String bowlerDetails=bowlerCard.get(j).getText();
				
				
				if(cellBowler==0) {
					  
					  XSSFRow row=sheet.getRow(lastRow);
				     	XSSFCell cell=row.createCell(cellBowler);
				     	XSSFCellStyle style=workbook.createCellStyle();
				     	
				     	XSSFFont font=workbook.createFont();
				     	font.setBold(false);
				     	font.setColor(color);
				     	style.setFont(font); 
				     	cell.setCellStyle(style);
				     	cell.setCellValue(bowlerDetails);
				  
				     	cellBowler++;
			        
				  }
				else if(cellBowler==4) {
					  
					  XSSFRow row=sheet.getRow(lastRow);
				     	XSSFCell cell=row.createCell(cellBowler);
				     	XSSFCellStyle style=workbook.createCellStyle();
				     	
				     	XSSFFont font=workbook.createFont();
				     	font.setBold(true);
				     	style.setFont(font); 
				     	cell.setCellStyle(style);
				     	cell.setCellValue(bowlerDetails);
				  
				     	cellBowler++;
			        
				  }
				
				else {
				
					 XSSFRow row=sheet.getRow(lastRow);
				     	XSSFCell cell=row.createCell(cellBowler);
				     	XSSFCellStyle style=workbook.createCellStyle();
				     	
				     	XSSFFont font=workbook.createFont();
				     	font.setBold(false);
				     	font.setColor(headerTextcolor);
				     	style.setFont(font); 
				     	cell.setCellStyle(style);
				     	cell.setCellValue(bowlerDetails);
				       cellBowler++;
				}
			}
			
			lastRow++;
			
			
		}
		
		}
		
	}
		
		catch(StaleElementReferenceException e) {
			
			e.getMessage();
		}	
	}
	
	
	
	private CellRangeAddress CellRangeAddress(int lastRow2, int cellFOW) {
		// TODO Auto-generated method stub
		return null;
	}



	public static void closeExcel() throws IOException {                                // Closing the excel file

	    fos=new FileOutputStream(source);
		workbook.write(fos);
		workbook.close();
	}

}
