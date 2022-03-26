package com.inetBankingV1.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest logger;	
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//timestamp
		String repName = "Test-Report-"+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(repName+".html");
		
		sparkReporter.config().setDocumentTitle("InetBanking Test Project"); //Title of Tab
		sparkReporter.config().setReportName("Functional Test Automation Report"); //Name of the report
		sparkReporter.config().setTheme(Theme.DARK); //Theme of project
		
	
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("Tester", "Uday");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
		
		String screenshotPath =  System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		
		File f = new File(screenshotPath);
		if(f.exists())
		{
			try
			{
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}
