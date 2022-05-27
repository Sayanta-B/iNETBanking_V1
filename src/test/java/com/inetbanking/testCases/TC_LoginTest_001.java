package com.inetbanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass {
	@Test
	public void loginTest() {
		driver.get(baseURL);
		Logger.info("URL loaded successfully");
		LoginPage lp = new LoginPage(driver);
		lp.setUsername(username);
		Logger.info("Entered username properly");
		lp.setPassword(password);
		Logger.info("Entered password properly");
		lp.clickSubmit();
		Logger.info("Submitted");
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage")){
			Assert.assertTrue(true);
			Logger.info("Login test passed");
		}
		else {
			Assert.assertTrue(false);
			Logger.info("Login test failed");
		}
		
	}

}
