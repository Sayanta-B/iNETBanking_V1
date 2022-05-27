package com.inetbanking.testCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.intbanking.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig rc=new ReadConfig();
	
	public String baseURL=rc.getApplicationURL();
	public String username=rc.getUserName();
	public String password=rc.getPassword();
	public static WebDriver driver;
	public Logger Logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");	
		Logger = Logger.getLogger("ebanking");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",rc.getChromepath());
			driver=new ChromeDriver();			
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.geko.driver",rc.getFireFoxpath());
			driver=new FirefoxDriver();		
		}		
	}
	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
