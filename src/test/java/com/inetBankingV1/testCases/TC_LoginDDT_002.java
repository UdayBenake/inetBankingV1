package com.inetBankingV1.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBankingV1.pageObjects.LoginPage;
import com.inetBankingV1.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd)
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		if(isAlertPresent() == true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false); // false because alert is displayed and alert will be displayed in case incorrect credentials entered.
		}
		else
		{
			Assert.assertTrue(true);// this will make our test pass as it has been entered into else part
			lp.clickLogout();
			driver.switchTo().alert().accept(); // this will accept the alert after clicking on logout link.
		}
		
	}
	
	public boolean isAlertPresent() // this is user defined method to check Alert is Present or NOT
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetBankingV1/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String[][] loginData = new String[rownum][colcount];
		
		for(int i=1; i<=rownum; i++)
		{
			for(int j=0; j<colcount; j++)
			{
			loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j); //i=row and j=column
			}
		}
		return loginData;
	}
}
