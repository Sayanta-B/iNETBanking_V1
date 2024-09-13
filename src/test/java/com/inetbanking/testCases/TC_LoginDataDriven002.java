package com.inetbanking.testCases;

import java.io.IOException;

import com.inetbanking.base.BaseClass;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDataDriven002 extends BaseClass {
	
	@Test(dataProvider="Data")
	public void loginDDT(String username,String password) throws InterruptedException {
		LoginPage lPage = new LoginPage(driver);
		lPage.setUsername(username);
		Logger.info("UserName provided");
		lPage.setPassword(password);
		Logger.info("UserName provided");
		lPage.clickSubmit();
		Thread.sleep(3000);
		
		if(isAlaetIsPresent()==true) {
			driver.switchTo().alert().accept(); //accept the alert to close the alert popup
			driver.switchTo().defaultContent();  //Move focus back to login page
			Assert.assertTrue(false);
			Logger.warn("-----------------Login failed----------------- ");
		}
		else {
			Assert.assertTrue(true);
			Logger.info("-----------------Login passed----------------- ");
			lPage.btnLogout();
			driver.switchTo().alert().accept(); //accept the logout alert to close the alert popup
			driver.switchTo().defaultContent();  //Move focus back to login page
		}
	}
	
	public boolean isAlaetIsPresent() {
		try {
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e) {
			
			return false;
		}
	}
	
	@DataProvider(name="Data")
	Object [][] getData() throws IOException{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/Data.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path,"Sheet1", 1);
		
		String logindata[][]=new String [rownum][colcount];
		
		for (int i=1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j); //1,0
			}
		}
		return logindata;
	}

}
