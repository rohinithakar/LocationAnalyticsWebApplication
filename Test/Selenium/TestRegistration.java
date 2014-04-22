package Selenium;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestRegistration extends SeleniumTestCase  {


	public void testRegistrationValidInput()  throws Exception{
		driver.get(baseUrl);
		waitForElementById("create-user");
		click("create-user");
		fillTextElement("fname", "John");
		fillTextElement("lname", "Doe");
		fillTextElement("email_reg", "j@yahoo.com");
		fillTextElement("password_reg", "123456");
		fillTextElement("question", "your car color");
		fillTextElement("answer", "grey");
		driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Home");
	}
	
	public void testRegistrationEmptyInput() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("create-user");
		click("create-user");
		fillTextElement("fname", "");
		fillTextElement("lname", "");
		fillTextElement("email_reg", "");
		fillTextElement("password_reg", "");
		fillTextElement("question", "");
		fillTextElement("answer", "");
		driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");
		
	}
	
	public void testRegistrationNullInput() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("create-user");
		click("create-user");
		fillTextElement("fname", null);
		fillTextElement("lname", null);
		fillTextElement("email_reg", null);
		fillTextElement("password_reg",null);
		fillTextElement("question", null);
		fillTextElement("answer", null);
		driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");	
	}
	
	public void testRegistrationInvalidUserName() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("create-user");
		click("create-user");
		fillTextElement("fname", "bcc");
		fillTextElement("lname", "");
		fillTextElement("email_reg", "dvdf");
		fillTextElement("password_reg","3434ddfd");
		fillTextElement("question", "why");
		fillTextElement("answer", "");
		driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");
		
	}
	
	public void testRegistrationInvalidPassword() throws InterruptedException{
		driver.get(baseUrl);
		waitForElementById("create-user");
		click("create-user");
		fillTextElement("fname", "bcc");
		fillTextElement("lname", "fgfgfg");
		fillTextElement("email_reg", "df@yahoo.com");
		fillTextElement("password_reg","");
		fillTextElement("question", "name");
		fillTextElement("answer", "dfd");
		driver.findElement(By.xpath("/html/body/div[4]/div[3]/div/button[1]/span")).click();
		wait2Sec();
		driver.getPageSource().contains("Invalid Input");
	}


}
