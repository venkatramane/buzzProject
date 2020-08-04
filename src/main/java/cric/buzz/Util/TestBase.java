package cric.buzz.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase(){
		
		try{
			prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\VENKATRAMAN\\workspace\\CricbuzzCucumber\\src\\main\\java\\cric\\buzz\\config\\config.properties");
			prop.load(fis);
		}catch(IOException e){
			e.getMessage();
		}
	}
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "E:\\WebDriver\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}else
		{
			System.out.println("Wrong Link");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
//		driver.manage().timeouts().pageLoadTimeout(TestUtilll.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(TestUtilll.IMPLICITY_WAIT, TimeUnit.SECONDS);
//		
//		driver.get(prop.getProperty("url"));
		
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}
	

}
