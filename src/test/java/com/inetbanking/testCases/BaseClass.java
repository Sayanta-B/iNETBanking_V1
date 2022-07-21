package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.intbanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

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
//			System.setProperty("webdriver.chrome.driver",rc.getChromepath());
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
			driver.manage().window().maximize();
		}
		else if(br.equals("firefox")) {
//			System.setProperty("webdriver.geko.driver",rc.getFireFoxpath());
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();	
			driver.manage().window().maximize();
		}
//		driver.manage().timeouts().implicitlyWait(10,TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(baseURL);
	}
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	public void captureScreen(WebDriver driver, String tname ) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
	}
	public static WebElement waitForElementPresent(WebDriver driver,By locator,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return driver.findElement(locator);
		
	}

}
