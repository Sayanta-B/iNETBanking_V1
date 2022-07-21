package com.intbanking.utilities;
//listener class uses to generate extent report
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	
	public ExtentReports extent;
	public ExtentSparkReporter spark;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) {
		String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String repName="Test-Report"+timeStamp+".html";
		spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\test-output"+repName);
		extent=new ExtentReports();
		
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "Sayanta");
		
		spark.config().setDocumentTitle("Internet Banking Test Project");
		spark.config().setReportName("Functional test Report");
		spark.config().setTheme(Theme.DARK);
		
	}
	public void onTestSuccess(ITestResult tr) {
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));//send pass test info
		
	}
	public void onTestFailure(ITestResult tr) {
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));//send failed test info
		
		String screenshotPath=System.getProperty("user dir")+"\\Scerrnshots"+tr.getName()+".png";
		
		File f =new File(screenshotPath);
		
		if (f.exists()) {
			try {
				logger.fail("Screenshots is below:"+logger.addScreenCaptureFromPath(screenshotPath));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}
	public void onTestSkipped(ITestResult tr) {
		logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));//send pass test info
		
	}
	public void onFinish(ITestContext testcontext) {
		extent.flush();
	}
}
//	private static final String CODE1 = "{\n    \"theme\": \"standard\",\n    \"encoding\": \"utf-8\n}";
//    private static final String CODE2 = "{\n    \"protocol\": \"HTTPS\",\n    \"timelineEnabled\": false\n}";

//    public static void main(String[] args) throws ClassNotFoundException {
//        ExtentReports extent = new ExtentReports();
//        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
//        extent.attachReporter(spark);
//        
        

//        extent.createTest("ScreenCapture")
//                .addScreenCaptureFromPath("extent.png")
//                .pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());
//
//        extent.createTest("LogLevels")
//                .info("info")
//                .pass("pass")
//                .warning("warn")
//                .skip("skip")
//                .fail("fail");
//
//        extent.createTest("CodeBlock").generateLog(
//                Status.PASS,
//                MarkupHelper.createCodeBlock(CODE1, CODE2));
//
//        extent.createTest("ParentWithChild")
//                .createNode("Child")
//                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");
//
//        extent.createTest("Tags")
//                .assignCategory("MyTag")
//                .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");
//
//        extent.createTest("Authors")
//                .assignAuthor("TheAuthor")
//                .pass("This test 'Authors' was assigned by a special kind of author tag.");
//
//        extent.createTest("Devices")
//                .assignDevice("TheDevice")
//                .pass("This test 'Devices' was assigned by a special kind of devices tag.");
//
//        extent.createTest("Exception! <i class='fa fa-frown-o'></i>")
//                .fail(new RuntimeException("A runtime exception occurred!"));

//        extent.flush();
//    }


