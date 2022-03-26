package com.inetBankingV1.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBankingV1.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass 
{
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL = readconfig.getApplicationURL();
	public String userName = readconfig.getUserName();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger log ;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br)
	{
		log = LogManager.getLogger(BaseClass.class.getName());
		log.info("Driver Launched");
		
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(br.equals("safari"))
		{
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseURL);
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public String randomString()
	{
	String generatedString = RandomStringUtils.randomAlphabetic(6);
	return(generatedString);
	}
	
	public String randomNum()
	{
	String generatedString2 = RandomStringUtils.randomNumeric(6);
	return(generatedString2);
	}
	
	
}
