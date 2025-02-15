package com.inetbanking.utilities;
//listener class uses to generate extent report
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;



public class Reporting extends TestListenerAdapter{
	

	
	public void onStart(ITestContext testContext) {
		String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report"+timeStamp+".html";


		
	}
	public void onTestSuccess(ITestResult tr) {

		
	}
	public void onTestFailure(ITestResult tr) {


	}
	public void onTestSkipped(ITestResult tr) {

		
	}
	public void onFinish(ITestContext testcontext) {


	}
}
