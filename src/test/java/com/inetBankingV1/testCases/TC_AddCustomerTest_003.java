package com.inetBankingV1.testCases;

import java.io.IOException;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.ScreenCapture;
import com.inetBankingV1.pageObjects.AddCustomerPage;
import com.inetBankingV1.pageObjects.LoginPage;
import com.inetBankingV1.utilities.screenCaptureUtils;

public class TC_AddCustomerTest_003 extends BaseClass
{
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPassword(password);
		lp.clickSubmit();
		
		Thread.sleep(10000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		
		addcust.clickAddNewCustomer();
		//Thread.sleep(10000);
		//driver.switchTo().frame("ad_iframe");
	//driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
		
		
		addcust.custName("Uday");
		addcust.custgender("male");
		addcust.custdob("04","08","1989");
		Thread.sleep(10000);
		addcust.custaddress("India");
		addcust.custcity("Pune");
		addcust.custstate("MH");
		addcust.custpinno("411040");
		addcust.custtelephoneno("8275360348");
		
		String email = randomString()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abadada");
		addcust.custsubmit();
		
		Thread.sleep(20000);
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
		}
		else
		{
			screenCaptureUtils ut = new screenCaptureUtils();
			ut.captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);
		}
	}
	

}
