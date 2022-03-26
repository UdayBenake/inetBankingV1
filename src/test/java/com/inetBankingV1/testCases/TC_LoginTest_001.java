package com.inetBankingV1.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBankingV1.pageObjects.LoginPage;
import com.inetBankingV1.utilities.screenCaptureUtils;

public class TC_LoginTest_001 extends BaseClass
{

	@Test
	public void loginTest() throws IOException
	{
		driver.get(baseURL);
		
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		
		lp.setPassword(password);
		
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			
			Assert.assertTrue(true);
		}
		else
		{
			screenCaptureUtils sc = new screenCaptureUtils();
			sc.captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}		
		
	}
}
