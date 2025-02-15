package com.inetbanking.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import com.inetbanking.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	@Test
	public void loginTest() throws IOException, InterruptedException {
		
		Logger.info("url loaded successfully");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		Logger.info("Entered username properly");
		lp.setPassword(password);
		Logger.info("Entered password properly");
		lp.clickSubmit();
		Logger.info("Submitted");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	
//		waitForElementPresent(driver,By.xpath(""),5);
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){

			Assert.assertTrue(true);
			Logger.info("Login test passed");
		}
		else {
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			Logger.info("Login test failed");
			
		}
		
	}

}
